package it.woodcast.services;

import it.woodcast.entity.BatchRegistryEntity;
import it.woodcast.entity.CustomerEntity;
import it.woodcast.repository.CustomerBatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceService {
    @Autowired
    private CustomerBatchRepository customerBatchRepository;
    public List<BatchRegistryEntity> findByCustomer(CustomerEntity id){
        return customerBatchRepository.findByCustomer(id);
    }
    public List<BatchRegistryEntity> findAll(){
        return customerBatchRepository.findAll();
    }

    public BatchRegistryEntity findById(Integer id){
        return customerBatchRepository.findById(id).get();
    }

    public BatchRegistryEntity save(BatchRegistryEntity customerServiceEntity){

        return customerBatchRepository.save(customerServiceEntity);
    }

}
