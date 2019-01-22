package emi.ac.ma.pi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import emi.ac.ma.pi.model.Domaine;

@RepositoryRestResource
public interface DomaineRepository extends JpaRepository<Domaine, Long> {

	Optional<Domaine> findByDomaine(String domaine);

}
