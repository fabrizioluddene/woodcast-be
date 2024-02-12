package it.woodcast.repository;

import it.woodcast.entity.BatchRegistryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchRegistryRepository  extends JpaRepository<BatchRegistryEntity, Integer> {

    @Query("select bre from BatchRegistryEntity bre  where bre.customer.id = :id order by order ")
    List<BatchRegistryEntity> findByCustomer(String id);
    @Query("select bre from BatchRegistryEntity bre  where bre.customer.id = :id and id = :idBatchRegistry order by order ")
    List<BatchRegistryEntity> findByCustomerAndId(String id,String idBatchRegistry);

}
