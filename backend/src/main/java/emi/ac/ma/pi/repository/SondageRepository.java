package emi.ac.ma.pi.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import emi.ac.ma.pi.model.Sondage;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@RepositoryRestResource
public interface SondageRepository extends JpaRepository<Sondage, Long>{
	Optional<Sondage> findBySondage(String sondage);
}
