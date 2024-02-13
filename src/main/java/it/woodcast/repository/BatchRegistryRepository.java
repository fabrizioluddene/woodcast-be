package it.woodcast.repository;

import it.woodcast.entity.BatchRegistryEntity;
import it.woodcast.entity.CustomerEntity;
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

    @Query("SELECT bre FROM BatchRegistryEntity bre JOIN bre.user u WHERE u.id = :userId and bre.customer.id = :id and bre.id = :idBatchRegistry order by order")
    List<BatchRegistryEntity> findByCustomerAndIdAndUserId(String id,String idBatchRegistry,String userId);

    @Query("SELECT bre FROM BatchRegistryEntity bre JOIN bre.user u WHERE u.id = :userId and bre.customer.id = :id order by order")
    List<BatchRegistryEntity> findByCustomerUserId(String id,String userId);

    List<BatchRegistryEntity> findByCustomer(CustomerEntity id);

    BatchRegistryEntity findByCustomerId(Integer id);

}
