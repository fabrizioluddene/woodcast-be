package it.woodcast.repository;

import it.woodcast.entity.BatchRegistryEntity;
import it.woodcast.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerBatchRepository extends JpaRepository<BatchRegistryEntity, Integer> {
    List<BatchRegistryEntity> findByCustomer(CustomerEntity id);

    BatchRegistryEntity findByCustomerId(Integer id);

}
