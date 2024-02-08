package it.woodcast.logic;

import it.woodcast.entity.BatchRegistryEntity;
import it.woodcast.entity.CustomerServiceEntity;
import it.woodcast.mapper.BatchRegistryMapper;
import it.woodcast.resources.BatchRegistry;
import it.woodcast.services.BatchRegistryServices;
import it.woodcast.services.CustomerServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BatchRegistryFacade extends BaseFacade{

    @Autowired
    private BatchRegistryServices batchRegistryServices;

    @Autowired
    private BatchRegistryMapper batchRegistryMapper;

    @Autowired
    private CustomerServiceService customerServiceService;

    public List<BatchRegistry> getBatchRegistryResources() {
        List<BatchRegistryEntity> batchRegistryEntities =  batchRegistryServices.findAll();
        List<BatchRegistry> batchRegistries = new ArrayList<>();
                batchRegistryEntities.stream().forEach(batchRegistryEntity -> {
                    BatchRegistry batchRegistry =  batchRegistryMapper.mapAtoB(batchRegistryEntity);
                    batchRegistry.setIdCustomerService(batchRegistryEntity.getServiceParam().getId());
                    batchRegistry.setCustomerService(batchRegistryEntity.getServiceParam().getName());
                    batchRegistry.setProceedsPlafond((BigDecimal.valueOf(batchRegistry.getProceedsDayPlafond())).multiply(batchRegistryEntity.getServiceParam().getRate()));
                    batchRegistry.setDaysRemaining(batchRegistry.getProceedsDayPlafond());
                    batchRegistry.setProceedsRemaining(batchRegistry.getProceedsPlafond());
                    batchRegistries.add(batchRegistry);
        });
        return batchRegistries;
    }
    public void save(BatchRegistry batchRegistry){
        CustomerServiceEntity customerServiceEntity = customerServiceService.findById(batchRegistry.getIdCustomerService());

        BatchRegistryEntity  batchRegistryEntity= modelMapper.map(batchRegistry,BatchRegistryEntity.class);
        batchRegistryEntity.setServiceParam(customerServiceEntity);
        batchRegistryServices.save(batchRegistryEntity);
    }
    public void delete(Integer id) {
        batchRegistryServices.delete(id);
    }
}
