package it.woodcast.repository;

import it.woodcast.entity.BatchRegistryEntity;
import it.woodcast.resources.BatchRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientServiceRepository extends JpaRepository<BatchRegistryEntity, Integer> {

}
