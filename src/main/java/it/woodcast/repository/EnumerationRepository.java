package it.woodcast.repository;

import it.woodcast.entity.CustomerEntity;
import it.woodcast.entity.RulesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnumerationRepository extends JpaRepository<RulesEntity, Integer> {
}
