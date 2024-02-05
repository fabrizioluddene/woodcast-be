package it.woodcast.repository;

import it.woodcast.entity.CustomerEntity;
import it.woodcast.entity.CustomerServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerServiceRepository extends JpaRepository<CustomerServiceEntity, Integer> {

    List<CustomerServiceEntity> findByCustomer(CustomerEntity id);

    CustomerServiceEntity findByCustomerId(Integer id);

}
