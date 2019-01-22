package emi.ac.ma.pi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import emi.ac.ma.pi.model.Proposition;

@RepositoryRestResource
public interface PropositionRepository extends JpaRepository<Proposition, Long> {
}
