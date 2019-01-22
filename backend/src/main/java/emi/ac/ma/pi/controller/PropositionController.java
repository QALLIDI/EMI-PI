package emi.ac.ma.pi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import emi.ac.ma.pi.model.Domaine;
import emi.ac.ma.pi.model.Proposition;
import emi.ac.ma.pi.model.Sondage;
import emi.ac.ma.pi.repository.PropositionRepository;
import emi.ac.ma.pi.repository.SondageRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/propositions")
public class PropositionController {
	@Autowired
	private PropositionRepository propositionRespository;
	@Autowired
	private SondageRepository s;

	@GetMapping("/all/{id}")
	public List<Proposition> getPropositionsBySondageId(@PathVariable("id") Long id) {
		List<Proposition> list = new ArrayList<>();
		Iterable<Proposition> props = propositionRespository.findAll().stream()
				.filter(p -> p.getSondage().getId().equals(id)).collect(Collectors.toList());
		props.forEach(list::add);
		return list;
	}
	@PostMapping("/create")
	public @Valid Proposition createProposition(@RequestBody Proposition p) {
		Optional<Sondage> sondage = s.findBySondage(p.getSondage().getSondage());
		p.setSondage(sondage.get());
		return propositionRespository.save(p);
	}

}
