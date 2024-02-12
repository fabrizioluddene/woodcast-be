package it.woodcast.logic;

import it.woodcast.entity.BatchRegistryEntity;
import it.woodcast.entity.CalendarEntity;
import it.woodcast.entity.CustomerEntity;
import it.woodcast.mapper.BatchRegistryMapper;
import it.woodcast.repository.CalendarRepository;
import it.woodcast.resources.BatchRegistry;
import it.woodcast.resources.Customer;
import it.woodcast.services.BatchRegistryServices;
import it.woodcast.services.CustomerServiceService;
import it.woodcast.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerFacade extends BaseFacade {
    @Autowired
    private CustomerServices customerServices;

    @Autowired
    private BatchRegistryServices batchRegistryServices;
    @Autowired
    private BatchRegistryMapper batchRegistryMapper;

    @Autowired
    private CustomerServiceService customerServicesService;

    @Autowired
    private CalendarRepository calendarRepository;

    public List<Customer> findAllCustomer() {


        List<Customer> customers = new ArrayList<>();
        customerServices.findAll().stream().forEach(customerEntity -> {
            Customer customer = this.modelMapper.map(customerEntity, Customer.class);
            customers.add(customer);
        });
        return customers;

    }

    public List<BatchRegistry> getAllCustomerBatchRegistry(String id) {
        BigDecimal ZERO = BigDecimal.ZERO;
        List<BatchRegistryEntity> batchRegistryEntities =  batchRegistryServices.findByCustomer(id);
        List<BatchRegistry> batchRegistries = new ArrayList<>();
        batchRegistryEntities.stream().forEach(batchRegistryEntity -> {

            BatchRegistry batchRegistry =  batchRegistryMapper.mapAtoB(batchRegistryEntity);

            List<CalendarEntity> calendarEntities = calendarRepository.getByCustomerServiceEntitiesOrderByMonth(batchRegistryEntity);


            calendarEntities.stream().forEach(calendarEntity->{
                BigDecimal effectiveCosts = calendarEntity.getResourceEntities().getRateParamEntity().getRate().multiply(calendarEntity.getWorkingDay());
                batchRegistry.setEffectiveCosts(batchRegistry.getEffectiveCosts().add(effectiveCosts).setScale(2, RoundingMode.HALF_UP));
                batchRegistry.setTotalEffectiveDay(batchRegistry.getTotalEffectiveDay().add(calendarEntity.getWorkingDay()).setScale(2, RoundingMode.HALF_UP));
            });

            batchRegistry.setCalculateMargin((batchRegistry.getProceeds().subtract(batchRegistry.getEffectiveCosts())).divide(batchRegistry.getProceeds(),2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)));
            if (ZERO.compareTo(batchRegistry.getEffectiveCosts()) != 0 && ZERO.compareTo(batchRegistry.getTotalEffectiveDay()) != 0 ){
                batchRegistry.setAverageRate(batchRegistry.getEffectiveCosts().divide(batchRegistry.getTotalEffectiveDay(),2, RoundingMode.HALF_UP));
            }

            batchRegistry.setEffectiveMUP(batchRegistry.getProceeds().subtract(batchRegistry.getEffectiveCosts()).setScale(2, RoundingMode.HALF_UP));

            BigDecimal expectedMarginEU = getExpectedMarginEU(batchRegistry);

            batchRegistry.setOverallCosts(batchRegistry.getProceeds().subtract(expectedMarginEU).setScale(2, RoundingMode.HALF_UP));
            batchRegistry.setDeltaEffectiveCost(batchRegistry.getOverallCosts().subtract(batchRegistry.getEffectiveCosts()).setScale(2, RoundingMode.HALF_UP));
            batchRegistry.setExpectedMarginEU(expectedMarginEU.setScale(2, RoundingMode.HALF_UP));
            batchRegistry.setProceedsPlafond((BigDecimal.valueOf(batchRegistry.getProceedsDayPlafond())).multiply(batchRegistryEntity.getVendorRate()).setScale(2, RoundingMode.HALF_UP));
            batchRegistry.setDaysRemaining(batchRegistry.getProceedsDayPlafond());
            batchRegistry.setProceedsRemaining(batchRegistry.getProceedsPlafond().setScale(2, RoundingMode.HALF_UP));
            batchRegistry.setCustomerService(batchRegistryEntity.getOrder());
            batchRegistries.add(batchRegistry);
        });

        return batchRegistries;
    }

    private static BigDecimal getExpectedMarginEU(BatchRegistry batchRegistry) {
        BigDecimal expectedMarginEU = (batchRegistry.getExpectedMargin().multiply(batchRegistry.getProceeds())).divide(BigDecimal.valueOf(100));
        return expectedMarginEU;
    }

    public List<BatchRegistry> getAllCustomerService(Integer customerId){
        List<BatchRegistry> customerServices =  new ArrayList<>();
        CustomerEntity customer = this.customerServices.findById(customerId);
        customerServicesService.findByCustomer(customer).stream().forEach(customerServiceEntity -> {
            BatchRegistry customerService = this.modelMapper.map(customerServiceEntity, BatchRegistry.class);
            customerServices.add(customerService);
        });
        return customerServices;
    }

    public Customer save(Customer customer){
        CustomerEntity customerEntity =  modelMapper.map(customer,CustomerEntity.class);
        CustomerEntity customerEntityReturn =   this.customerServices.save(customerEntity);
        return modelMapper.map(customerEntityReturn,Customer.class);
    }
}