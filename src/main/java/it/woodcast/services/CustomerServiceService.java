package it.woodcast.services;

import it.woodcast.entity.CustomerEntity;
import it.woodcast.entity.CustomerServiceEntity;
import it.woodcast.repository.CustomerServiceRepository;
import it.woodcast.resources.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceService {
    @Autowired
    private CustomerServiceRepository customerServiceRepository;
    public List<CustomerServiceEntity> findByCustomer(CustomerEntity id){
        return customerServiceRepository.findByCustomer(id);
    }
    public List<CustomerServiceEntity> findAll(){
        return customerServiceRepository.findAll();
    }

    public CustomerServiceEntity findById(Integer id){
        return customerServiceRepository.findById(id).get();
    }

    public CustomerServiceEntity save(CustomerServiceEntity customerServiceEntity){

        return customerServiceRepository.save(customerServiceEntity);
    }

}
