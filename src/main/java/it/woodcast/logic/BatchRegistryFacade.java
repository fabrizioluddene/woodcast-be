package it.woodcast.logic;

import it.woodcast.entity.BatchRegistryEntity;
import it.woodcast.entity.CustomerEntity;
import it.woodcast.entity.UserEntity;
import it.woodcast.mapper.BatchRegistryMapper;
import it.woodcast.resources.BatchRegistry;
import it.woodcast.services.BatchRegistryServices;
import it.woodcast.services.CalendarService;
import it.woodcast.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class BatchRegistryFacade extends BaseFacade{


    @Autowired
    private BatchRegistryServices batchRegistryServices;

    @Autowired
    private BatchRegistryMapper batchRegistryMapper;

    @Autowired
    private CustomerServices customerService;

    @Autowired
    private CalendarService calendarService;

    public List<BatchRegistry> getBatchRegistryResources() {

        List<BatchRegistryEntity> batchRegistryEntities =  batchRegistryServices.findAll();
        List<BatchRegistry> batchRegistries = new ArrayList<>();
                batchRegistryEntities.stream().forEach(batchRegistryEntity -> {
                    BatchRegistry batchRegistry =  batchRegistryMapper.mapAtoB(batchRegistryEntity);
                    batchRegistry.setIdCustomerService(batchRegistryEntity.getId());
                    batchRegistry.setCustomerService(batchRegistryEntity.getOrder());
                    batchRegistry.setProceedsPlafond((BigDecimal.valueOf(batchRegistry.getProceedsDayPlafond())).multiply(batchRegistryEntity.getVendorRate()));
                    batchRegistry.setDaysRemaining(batchRegistry.getProceedsDayPlafond());
                    batchRegistry.setProceedsRemaining(batchRegistry.getProceedsPlafond());
                    batchRegistries.add(batchRegistry);
        });
        return batchRegistries;
    }
    public void save(BatchRegistry batchRegistry){
        CustomerEntity customerEntity = customerService.findById(batchRegistry.getIdCustomerService());

        BatchRegistryEntity  batchRegistryEntity= modelMapper.map(batchRegistry,BatchRegistryEntity.class);
        batchRegistryEntity.setCustomer(customerEntity);
        List<UserEntity> userEntities =new ArrayList<>();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(Integer.parseInt(batchRegistryEntity.getPm()));
        userEntities.add(userEntity);
        batchRegistryEntity.setUser(userEntities);
        batchRegistryServices.save(batchRegistryEntity);
    }

    public void deleteAllByCustomer(Integer id){
        calendarService.deleteAllByCustomer(id);
        batchRegistryServices.delete(id);
    }
}
