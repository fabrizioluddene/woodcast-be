package it.woodcast.services;

import it.woodcast.entity.CustomerEntity;
import it.woodcast.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServices {
    @Autowired
    private CustomerRepository customerRepository;
    public List<CustomerEntity> findAll() {


        return customerRepository.findAll();
    }

    public CustomerEntity findById(Integer id){
        return customerRepository.findById(id).get();
    }
    public CustomerEntity save(CustomerEntity customerEntity) {

        CustomerEntity customerEntityRt = customerRepository.save(customerEntity);
        return customerEntityRt;
    }

}
