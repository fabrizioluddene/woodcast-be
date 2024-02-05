package it.woodcast.logic;

import it.woodcast.entity.CustomerEntity;
import it.woodcast.entity.CustomerServiceEntity;
import it.woodcast.resources.CustomerService;
import it.woodcast.services.CustomerServiceService;
import it.woodcast.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceFacade extends BaseFacade {
    @Autowired
    CustomerServiceService customerServiceService;

    @Autowired
    CustomerServices customerServices;


    public List<CustomerService> findAll() {
        List<CustomerService> customerServicesList = new ArrayList<>();
        customerServiceService.findAll().stream().forEach(customerServiceEntity -> {
                    customerServicesList.add(modelMapper.map(customerServiceEntity, CustomerService.class));
                }
        );
        return customerServicesList;
    }

    public CustomerService save(CustomerService customerService, Integer customeId) {
        CustomerEntity customer = customerServices.findById(customeId);
        CustomerServiceEntity customerEntity = modelMapper.map(customerService, CustomerServiceEntity.class);
        customerEntity.setCustomer(customer);
        CustomerServiceEntity customerEntityReturn = this.customerServiceService.save(customerEntity);
        return modelMapper.map(customerEntityReturn, CustomerService.class);
    }
}
