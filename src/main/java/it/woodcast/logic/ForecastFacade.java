package it.woodcast.logic;

import it.woodcast.entity.BatchRegistryEntity;
import it.woodcast.entity.CalendarEntity;
import it.woodcast.entity.WorkingCalendarEntity;
import it.woodcast.enumeration.RulesEnum;
import it.woodcast.mapper.BatchRegistryMapper;
import it.woodcast.repository.CalendarRepository;
import it.woodcast.repository.WorkingCalendarRepository;
import it.woodcast.resources.*;
import it.woodcast.resources.dashboard.graph.CalendarGraphDashbordResource;
import it.woodcast.services.BatchRegistryServices;
import it.woodcast.services.CustomerServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
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
    WorkingCalendarRepository workingCalendarRepository;


    @Autowired
    private CalendarRepository calendarRepository;
    private static final BigDecimal ZERO = BigDecimal.ZERO;
    private static final BigDecimal U = BigDecimal.valueOf(100);




    public List<CalendarGraphDashbordResource>  getAllCustomerBatchRegistryDashboard(String id, String batchRegistriId)  {

        List<BatchRegistryEntity> batchRegistryEntities = getBatchRegistryEntities(id, batchRegistriId);

        Map<String, Pivot> pivotMap= new HashMap<>();
        List<Integer> ids= new ArrayList<>();

        batchRegistryEntities.stream().forEach(batchRegistryEntity -> {
            ids.add(batchRegistryEntity.getId());

        });
        List<CalendarEntity> calendarEntities = calendarRepository.findByBatch(ids);
        for (CalendarEntity calendarEntity : calendarEntities) {
            BigDecimal numeroGiorni = calendarEntity.getWorkingDay();
            BigDecimal proceeds = calendarEntity.getCustomerServiceEntities().getExpectedMargin();
            BigDecimal rate = calendarEntity.getResourceEntities().getRateParamEntity().getRate();
            Pivot pivot = pivotMap.computeIfAbsent(dateToString(calendarEntity.getMonth()),k-> new Pivot());
            BigDecimal calculatedCost = rate.multiply(numeroGiorni).setScale(2, RoundingMode.HALF_UP);
            BigDecimal calculatedPerceed = ZERO;
            if (ZERO.compareTo(calculatedCost) != 0) {
                calculatedPerceed = calculatedCost.divide(U.subtract(proceeds).divide(U), 2, RoundingMode.HALF_UP);
            }
            pivot.setCalculatedProceeds(pivot.getCalculatedProceeds().add(calculatedPerceed));
            pivot.setCalculatedCost(pivot.getCalculatedCost().add(calculatedCost));

        }

        List<CalendarGraphDashbordResource> calendarGraphDashbordResource = new ArrayList<>();

        CalendarGraphDashbordResource calendarGraphDashbordResourceProceeds =  new CalendarGraphDashbordResource();
        List<BigDecimal> value = new ArrayList<>();
        if (pivotMap.size() !=0) {
            value.add(pivotMap.get("gennaio").getCalculatedProceeds());
            value.add(pivotMap.get("febbraio").getCalculatedProceeds());
            value.add(pivotMap.get("marzo").getCalculatedProceeds());
            value.add(pivotMap.get("aprile").getCalculatedProceeds());
            value.add(pivotMap.get("maggio").getCalculatedProceeds());
            value.add(pivotMap.get("giugno").getCalculatedProceeds());
            value.add(pivotMap.get("luglio").getCalculatedProceeds());
            value.add(pivotMap.get("agosto").getCalculatedProceeds());
            value.add(pivotMap.get("settembre").getCalculatedProceeds());
            value.add(pivotMap.get("ottobbre").getCalculatedProceeds());
            value.add(pivotMap.get("novembre").getCalculatedProceeds());
            value.add(pivotMap.get("dicembre").getCalculatedProceeds());
        }

        calendarGraphDashbordResourceProceeds.setData(value);
        calendarGraphDashbordResourceProceeds.setName("Ricavi");
        calendarGraphDashbordResource.add(calendarGraphDashbordResourceProceeds);

        CalendarGraphDashbordResource calendarGraphDashbordResourceCost =  new CalendarGraphDashbordResource();
        List<BigDecimal> valueCost = new ArrayList<>();
        if (pivotMap.size() !=0) {
            valueCost.add(pivotMap.get("gennaio").getCalculatedCost());
            valueCost.add(pivotMap.get("febbraio").getCalculatedCost());
            valueCost.add(pivotMap.get("marzo").getCalculatedCost());
            valueCost.add(pivotMap.get("aprile").getCalculatedCost());
            valueCost.add(pivotMap.get("maggio").getCalculatedCost());
            valueCost.add(pivotMap.get("giugno").getCalculatedCost());
            valueCost.add(pivotMap.get("luglio").getCalculatedCost());
            valueCost.add(pivotMap.get("agosto").getCalculatedCost());
            valueCost.add(pivotMap.get("settembre").getCalculatedCost());
            valueCost.add(pivotMap.get("ottobbre").getCalculatedCost());
            valueCost.add(pivotMap.get("novembre").getCalculatedCost());
            valueCost.add(pivotMap.get("dicembre").getCalculatedCost());
        }
        calendarGraphDashbordResourceCost.setData(valueCost);
        calendarGraphDashbordResourceCost.setName("Costi");
        calendarGraphDashbordResource.add(calendarGraphDashbordResourceCost);


        return calendarGraphDashbordResource;
    }

    public List<CalendarPivotResource> getAllCustomerBatchRegistry(String id, String batchRegistriId)  {

        List<BatchRegistryEntity> batchRegistryEntities = getBatchRegistryEntities(id, batchRegistriId);
        WorkingCalendarEntity workingCalendarEntity= workingCalendarRepository.findByYear(new BigDecimal(2024));
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

    private BigDecimal getWorkableDays(WorkingCalendarEntity workingCalendarEntity,Date data){
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String dataString = sdf.format(data);

        BigDecimal workableDays = BigDecimal.ZERO;
        switch (dataString) {
            case "01":
                return workingCalendarEntity.getJanuary();

            case "02":
                return workingCalendarEntity.getFebruary();
            case "03":
                return workingCalendarEntity.getMarch();
            case "04":
                return workingCalendarEntity.getApril();
            case "05":
                return workingCalendarEntity.getMay();
            case "06":
                return workingCalendarEntity.getJune();
            case "07":
                return workingCalendarEntity.getJuly();
            case "08":
                return workingCalendarEntity.getAugust();
            case "09":
                return workingCalendarEntity.getSeptember();
            case "10":
                return workingCalendarEntity.getOctober();
            case "11":
                return workingCalendarEntity.getNovember();
            case "12":
                return workingCalendarEntity.getDecember();
            default:
                return BigDecimal.ZERO;
        }

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
