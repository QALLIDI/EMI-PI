package emi.ac.ma.pi.controller;

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
@RequestMapping("/domaines")
public class DomaineController {
	@Autowired
	private DomaineRepository domaineRepository;
	@Autowired
	private SondageRepository sondageRepository;

	@GetMapping("/all")
	public List<Domaine> getAllDomaines() {
		List<Domaine> list = new ArrayList<>();
		Iterable<Domaine> domaines = domaineRepository.findAll();
		domaines.forEach(list::add);
		return list;
	}

	@PostMapping("/create")
	public @Valid Domaine createDomaine(@RequestBody Domaine domaine) {
		return domaineRepository.save(domaine);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Domaine> getDomaineById(@PathVariable("id") Long id) {
		Optional<Domaine> domaine = domaineRepository.findById(id);
		if (domaine.isPresent()) {
			return new ResponseEntity<Domaine>(domaine.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Domaine>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Domaine> updateDomaine(@PathVariable("id") Long id, @RequestBody Domaine domaine) {
		Optional<Domaine> optional = domaineRepository.findById(id);
		if (optional.isPresent()) {
			Domaine savedDomaine = optional.get();
			savedDomaine.setDomaine(savedDomaine.getDomaine());
			Domaine updatedDomaine = domaineRepository.save(savedDomaine);
			return new ResponseEntity<Domaine>(updatedDomaine, HttpStatus.OK);
		} else {
			return new ResponseEntity<Domaine>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Domaine> deleteDomaineById(@PathVariable("id") Long id) throws Exception {
		try {
			domaineRepository.deleteById(id);
		} catch (Exception e) {
			return new ResponseEntity<Domaine>(HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<Domaine>(HttpStatus.OK);
	}

	@GetMapping("/sondages/{id}")
	public List<Sondage> getAllSondages(@PathVariable("id") Long id) {
		List<Sondage> list = new ArrayList<>();
		Iterable<Sondage> domaines = sondageRepository.findAll().stream().filter(d -> d.getDomaine().getId().equals(id))
				.collect(Collectors.toList());
		domaines.forEach(list::add);
		return list;
	}
}
