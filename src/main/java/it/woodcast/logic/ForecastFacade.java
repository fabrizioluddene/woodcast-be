package it.woodcast.logic;

import it.woodcast.entity.BatchRegistryEntity;
import it.woodcast.entity.CalendarEntity;
import it.woodcast.entity.CustomerServiceEntity;
import it.woodcast.mapper.BatchRegistryMapper;
import it.woodcast.repository.CalendarRepository;
import it.woodcast.resources.*;
import it.woodcast.services.BatchRegistryServices;
import it.woodcast.services.CustomerServiceService;
import it.woodcast.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ForecastFacade extends BaseFacade{
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

    public List< CalendarPivotResource> getAllCustomerBatchRegistry(String id) {
        List< CalendarPivotResource> calendarPivotResources =  new ArrayList<>();
        List<BatchRegistryEntity> batchRegistryEntities = batchRegistryServices.findByCustomer(id);
        Map<String, CalendarPivotResource> pivotMap = new HashMap<>();
        batchRegistryEntities.stream().forEach(batchRegistryEntity -> {
            CustomerServiceEntity customerServiceEntity = batchRegistryEntity.getServiceParam();
            List<CalendarEntity> calendarEntities = calendarRepository.getByCustomerServiceEntitiesOrderByMonth(customerServiceEntity);
            String batchRegistryName= batchRegistryEntity.getOrder();
            Integer  batchRegistryId = batchRegistryEntity.getId();
            calendarEntities.stream().forEach(calendarEntity -> {
                String nome = calendarEntity.getResourceEntities().getNominative();
                String data = dateToString(calendarEntity.getMonth());
                BigDecimal numeroGiorni = calendarEntity.getWorkingDay();
                String key = calendarEntity.getResourceEntities().getId()+"-"+batchRegistryId;
                CalendarPivotResource pivotData = pivotMap.computeIfAbsent(key, k -> new CalendarPivotResource());
                pivotData.setNominative(nome);
                pivotData.setBatchRegistryName(batchRegistryName);
                pivotData.setRate(calendarEntity.getResourceEntities().getRateParamEntity().getRate());
                pivotData.setId(calendarEntity.getResourceEntities().getId());
                pivotData.setGrade(calendarEntity.getResourceEntities().getRateParamEntity().getGrade());
                pivotData.setCompany(calendarEntity.getResourceEntities().getCompany());
                pivotData.setArea(calendarEntity.getResourceEntities().getArea());
                Map<String, Pivot> pivot = pivotData.getPivot();
                Pivot pivot1 =  new Pivot();
                pivot1.setIdCalendar(calendarEntity.getId());
                pivot1.setWorkingDay(numeroGiorni);
                pivot.put(data, pivot1);
            });
        });


        return  new ArrayList<>(pivotMap.values());
    }

    private BigDecimal getExpectedMarginEU(BatchRegistry batchRegistry) {
        BigDecimal expectedMarginEU = (batchRegistry.getExpectedMargin().multiply(batchRegistry.getProceeds())).divide(BigDecimal.valueOf(100));
        return expectedMarginEU;
    }

    private String dateToString(Date data) {
        Map<String,String> stringStringMap =  new HashMap<>();
        stringStringMap.put("01","gennaio");
        stringStringMap.put("02","febbraio");
        stringStringMap.put("03","marzo");
        stringStringMap.put("04","aprile");
        stringStringMap.put("05","maggio");
        stringStringMap.put("06","giugno");
        stringStringMap.put("07","luglio");
        stringStringMap.put("08","agosto");
        stringStringMap.put("09","settembre");
        stringStringMap.put("10","ottobbre");
        stringStringMap.put("11","novembre");
        stringStringMap.put("12","dicembre");
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String dataString = sdf.format(data);
        return stringStringMap.get(dataString);
    }
}
