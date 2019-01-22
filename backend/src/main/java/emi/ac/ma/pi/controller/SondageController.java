package emi.ac.ma.pi.controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import emi.ac.ma.pi.model.Domaine;
import emi.ac.ma.pi.model.Sondage;
import emi.ac.ma.pi.repository.DomaineRepository;
import emi.ac.ma.pi.repository.SondageRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/sondages")
public class SondageController {
	@Autowired
	private SondageRepository sondageRepository;
	@Autowired
	private DomaineRepository domaineRepository;

	@GetMapping("/all")
	public List<Sondage> getAllSondages() {
		List<Sondage> list = new ArrayList<>();
		Iterable<Sondage> sondages = sondageRepository.findAll();
		sondages.forEach(list::add);
		return list;
	}

	@GetMapping("/encours")
	public List<Sondage> getAllSondagesEnCours() {
		List<Sondage> list = new ArrayList<>();
		Iterable<Sondage> sondages = sondageRepository.findAll().stream()
				.filter(sondage -> sondage.getTimeToFinish().isAfter(Instant.now())).collect(Collectors.toList());
		sondages.forEach(list::add);
		return list;
	}

	@GetMapping("/terminee")
	public List<Sondage> getAllSondagesTerminee() {
		List<Sondage> list = new ArrayList<>();
		Iterable<Sondage> sondages = sondageRepository.findAll().stream()
				.filter(sondage -> sondage.getTimeToFinish().isBefore(Instant.now())).collect(Collectors.toList());
		sondages.forEach(list::add);
		return list;
	}

	@PostMapping("/create")
	public Sondage createSondage(@Valid @RequestBody Sondage sondage) {
		Optional<Domaine> domaine = domaineRepository.findByDomaine(sondage.getDomaine().getDomaine());
		sondage.setDomaine(domaine.get());
		sondage.setTimeByHours(sondage.getTimeByHours());
		return sondageRepository.saveAndFlush(sondage);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Sondage> getSondageById(@PathVariable("id") Long id) {
		Optional<Sondage> sondage = sondageRepository.findById(id);
		if (sondage.isPresent()) {
			return new ResponseEntity<Sondage>(sondage.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Sondage>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Sondage> updateSondage(@PathVariable("id") Long id, @RequestBody Sondage sondage) {
		Optional<Sondage> optional = sondageRepository.findById(id);
		if (optional.isPresent()) {
			Sondage savedSondage = optional.get();
			savedSondage.setTitre(sondage.getTitre());
			savedSondage.setSondage(sondage.getSondage());
			savedSondage.setDomaine(sondage.getDomaine());
			savedSondage.setPropositions(sondage.getPropositions());

			Sondage updatedSondage = sondageRepository.save(savedSondage);
			return new ResponseEntity<Sondage>(updatedSondage, HttpStatus.OK);
		} else {
			return new ResponseEntity<Sondage>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Sondage> deleteSondageById(@PathVariable("id") Long id) throws Exception {
		try {
			sondageRepository.deleteById(id);
		} catch (Exception e) {
			return new ResponseEntity<Sondage>(HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<Sondage>(HttpStatus.OK);
	}
}
