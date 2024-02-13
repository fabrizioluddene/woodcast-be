package it.woodcast.logic;

import it.woodcast.entity.BatchRegistryEntity;
import it.woodcast.entity.CalendarEntity;
import it.woodcast.enumeration.RulesEnum;
import it.woodcast.mapper.BatchRegistryMapper;
import it.woodcast.repository.CalendarRepository;
import it.woodcast.resources.*;
import it.woodcast.services.BatchRegistryServices;
import it.woodcast.services.CustomerServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

@Component

public class ForecastFacade extends BaseFacade {
    @Autowired
    private CustomerServices customerServices;

    @Autowired
    private BatchRegistryServices batchRegistryServices;
    @Autowired
    private BatchRegistryMapper batchRegistryMapper;



    @Autowired
    private CalendarRepository calendarRepository;
    private static final BigDecimal ZERO = BigDecimal.ZERO;
    private static final BigDecimal U = BigDecimal.valueOf(100);




    public List<CalendarPivotResource> getAllCustomerBatchRegistry(String id, String batchRegistriId) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        List<BatchRegistryEntity> batchRegistryEntities = getBatchRegistryEntities(id, batchRegistriId);

        Map<String, CalendarPivotResource> pivotMap = new HashMap<>();
        batchRegistryEntities.stream().forEach(batchRegistryEntity -> {

            List<CalendarEntity> calendarEntities = calendarRepository.getByCustomerServiceEntitiesOrderByMonth(batchRegistryEntity);
            String batchRegistryName = batchRegistryEntity.getOrder();
            Integer batchRegistryId = batchRegistryEntity.getId();
            BigDecimal proceeds = batchRegistryEntity.getExpectedMargin();
            calendarEntities.stream().forEach(calendarEntity -> {
                String nome = calendarEntity.getResourceEntities().getNominative();
                String data = dateToString(calendarEntity.getMonth());
                BigDecimal rate = calendarEntity.getResourceEntities().getRateParamEntity().getRate();
                BigDecimal numeroGiorni = calendarEntity.getWorkingDay();
                String key = calendarEntity.getResourceEntities().getId() + "-" + batchRegistryId;
                CalendarPivotResource pivotData = pivotMap.computeIfAbsent(key, k -> new CalendarPivotResource());
                pivotData.setNominative(nome);
                pivotData.setBatchRegistryName(batchRegistryName);
                pivotData.setRate(calendarEntity.getResourceEntities().getRateParamEntity().getRate());
                pivotData.setId(calendarEntity.getResourceEntities().getId());
                pivotData.setGrade(calendarEntity.getResourceEntities().getRateParamEntity().getGrade());
                pivotData.setCompany(calendarEntity.getResourceEntities().getCompany());
                pivotData.setArea(calendarEntity.getResourceEntities().getArea());
                Map<String, Pivot> pivot = pivotData.getPivot();
                Pivot pivot1 = new Pivot();
                pivot1.setIdCalendar(calendarEntity.getId());
                pivot1.setWorkingDay(numeroGiorni);
                BigDecimal calculatedCost = rate.multiply(numeroGiorni).setScale(2, RoundingMode.HALF_UP);
                pivot1.setCalculatedCost(calculatedCost);
                BigDecimal calculatedPerceed = ZERO;
                if (ZERO.compareTo(calculatedCost) != 0) {
                    calculatedPerceed = calculatedCost.divide(U.subtract(proceeds).divide(U), 2, RoundingMode.HALF_UP);
                }
                pivot1.setCalculatedProceeds(calculatedPerceed);
                pivot.put(data, pivot1);
            });
        });


        return new ArrayList<>(pivotMap.values());
    }

    private List<BatchRegistryEntity> getBatchRegistryEntities(String id, String batchRegistriId) {
        JwtUser jwtUser = this.getJwtUser();
        List<BatchRegistryEntity> batchRegistryEntities;

        if(jwtUser.getRules().contains(RulesEnum.PRACTICE_LEADER)) {
            if (batchRegistriId == null || "".equals(batchRegistriId)) {
                batchRegistryEntities = batchRegistryServices.findByCustomer(id);
            } else {
                batchRegistryEntities = batchRegistryServices.findByCustomerAndId(id, batchRegistriId);
            }
        }else{
            if (batchRegistriId == null || "".equals(batchRegistriId)) {
                batchRegistryEntities = batchRegistryServices.findByCustomerUserId(id, jwtUser.getUserId());
            } else {
                batchRegistryEntities = batchRegistryServices.findByCustomerAndIdAndUserId(id, batchRegistriId, jwtUser.getUserId());
            }
        }
        return batchRegistryEntities;
    }

    public RevenuesCostsResource calculate(String id, String batchRegistriId) {
        RevenuesCostsResource revenuesCostsResource = new RevenuesCostsResource();
        List<BatchRegistryEntity> batchRegistryEntities = getBatchRegistryEntities(id, batchRegistriId);
        Map<String, CalendarPivotResource> pivotMap = new HashMap<>();
        batchRegistryEntities.stream().forEach(batchRegistryEntity -> {
            BigDecimal proceeds = batchRegistryEntity.getExpectedMargin();
            revenuesCostsResource.setExpectingPreceed(revenuesCostsResource.getExpectingPreceed().add(batchRegistryEntity.getProceeds()));
            List<CalendarEntity> calendarEntities = calendarRepository.getByCustomerServiceEntitiesOrderByMonth(batchRegistryEntity);

            calendarEntities.stream().forEach(calendarEntity -> {
                BigDecimal rate = calendarEntity.getResourceEntities().getRateParamEntity().getRate();
                BigDecimal numeroGiorni = calendarEntity.getWorkingDay();
                BigDecimal calculatedCost = rate.multiply(numeroGiorni).setScale(2, RoundingMode.HALF_UP);
                revenuesCostsResource.setCosts(revenuesCostsResource.getCosts().add(calculatedCost));
                revenuesCostsResource.setRevenues(revenuesCostsResource.getRevenues().add(calculatedCost.divide(U.subtract(proceeds).divide(U), 2, RoundingMode.HALF_UP)));

            });

        });

        revenuesCostsResource.setStoplight(evaluateStolight(revenuesCostsResource.getExpectingPreceed(), revenuesCostsResource.getRevenues()));

        return revenuesCostsResource;
    }

    private BigDecimal getExpectedMarginEU(BatchRegistry batchRegistry) {
        BigDecimal expectedMarginEU = (batchRegistry.getExpectedMargin().multiply(batchRegistry.getProceeds())).divide(BigDecimal.valueOf(100));
        return expectedMarginEU;
    }

    private StoplightEnum evaluateStolight(BigDecimal expectingPreceed, BigDecimal revenues) {
        BigDecimal diff = expectingPreceed.subtract(revenues);
        if (diff.compareTo(new BigDecimal("-1000")) < 0) {
            return StoplightEnum.RED;
        } else if (diff.compareTo(new BigDecimal("1000")) > 0) {
            return StoplightEnum.GREEN;
        } else if (diff.compareTo(new BigDecimal("-1000")) <= 0 && diff.compareTo(new BigDecimal("0")) <= 0) {
            return StoplightEnum.YELLOW;
        } else if (diff.compareTo(new BigDecimal("1000")) <= 0 && diff.compareTo(new BigDecimal("0")) >= 0) {
            return StoplightEnum.YELLOW;
        }
        return StoplightEnum.GREEN;
    }

    private String dateToString(Date data) {
        Map<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put("01", "gennaio");
        stringStringMap.put("02", "febbraio");
        stringStringMap.put("03", "marzo");
        stringStringMap.put("04", "aprile");
        stringStringMap.put("05", "maggio");
        stringStringMap.put("06", "giugno");
        stringStringMap.put("07", "luglio");
        stringStringMap.put("08", "agosto");
        stringStringMap.put("09", "settembre");
        stringStringMap.put("10", "ottobbre");
        stringStringMap.put("11", "novembre");
        stringStringMap.put("12", "dicembre");
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String dataString = sdf.format(data);
        return stringStringMap.get(dataString);
    }
}
