package it.woodcast.repository;

import it.woodcast.entity.CustomerServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientServiceRepository extends JpaRepository<CustomerServiceEntity, Integer> {

}
