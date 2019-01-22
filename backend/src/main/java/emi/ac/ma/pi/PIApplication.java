package emi.ac.ma.pi;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import emi.ac.ma.pi.model.Domaine;
import emi.ac.ma.pi.model.Proposition;
import emi.ac.ma.pi.model.Sondage;
import emi.ac.ma.pi.repository.DomaineRepository;
import emi.ac.ma.pi.repository.PropositionRepository;
import emi.ac.ma.pi.repository.SondageRepository;

@SpringBootApplication
public class PIApplication implements CommandLineRunner {
	@Autowired
	private DomaineRepository d;
	@Autowired
	private SondageRepository s;
	@Autowired
	private PropositionRepository p;

	public static void main(String[] args) {
		SpringApplication.run(PIApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*Domaine informatique = new Domaine("Informatique");
		Domaine politique = new Domaine("Politique");
		List<Domaine> domaines = Arrays.asList(informatique, politique);
		d.saveAll(domaines);*/
		/*Optional<Domaine> optional1 = d.findById(1L);
		//Optional<Domaine> optional2 = d.findById(2L);
		Sondage sondage6 = new Sondage("sondage 6", "avez vous deja une experience en Machine Learning ?", optional1.get(), 24L);
		s.save(sondage6);
		Optional<Sondage> optional6 = s.findById(6L);
		Proposition proposition1 = new Proposition("Oui", optional6.get());
		Proposition proposition2 = new Proposition("Non", optional6.get());
		p.save(proposition1);p.save(proposition2);*/
		/*
		Sondage sondage3 = new Sondage("sondage 3", "avez vous deja une experience en AI ?", optional1.get(), 12L);
		Sondage sondage4 = new Sondage("sondage 4", "avez vous deja une experience en Politique ?", optional2.get(),
				5L);
		Sondage sondage5 = new Sondage("sondage 5", "qu'est ce que vous pensez concernant la politique au Maroc ?",
				optional2.get(), 2L);
		Sondage sondage1 = new Sondage("sondage 1", "avez vous deja une experience en spring ?", optional1.get(),
				4L);
		Sondage sondage2 = new Sondage("sondage 2", "avez vous deja une experience en j2ee ?", optional1.get(), 3L);
		
		List<Sondage> sondages = Arrays.asList(sondage1, sondage2, sondage3, sondage4, sondage5);
		s.saveAll(sondages);
		Optional<Sondage> optional3 = s.findById(1L);
		Optional<Sondage> optional4 = s.findById(2L);
		Optional<Sondage> optional5 = s.findById(3L);
		Optional<Sondage> optional6 = s.findById(4L);
		Optional<Sondage> optional7 = s.findById(5L);
		
		Proposition proposition1 = new Proposition("Oui", optional3.get());
		Proposition proposition2 = new Proposition("Non", optional3.get());
		Proposition proposition3 = new Proposition("Oui", optional4.get());
		Proposition proposition4 = new Proposition("Non", optional4.get());
		Proposition proposition5 = new Proposition("Oui", optional5.get());
		Proposition proposition6 = new Proposition("Non", optional5.get());
		Proposition proposition7 = new Proposition("Oui", optional6.get());
		Proposition proposition8 = new Proposition("Non", optional6.get());
		Proposition proposition9 = new Proposition("Je pense que le Maroc est stable politiquement.", optional7.get());
		Proposition proposition10 = new Proposition("Je pense que le Maroc est instable politiquement.",
				optional6.get());
		List<Proposition> propositions = Arrays.asList(proposition1, proposition2, proposition3, proposition4,
				proposition5, proposition6, proposition7, proposition8, proposition9, proposition10);
		p.saveAll(propositions);*/
	}

}
