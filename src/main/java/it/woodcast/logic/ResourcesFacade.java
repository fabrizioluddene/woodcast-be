package it.woodcast.logic;

import it.woodcast.entity.*;
import it.woodcast.repository.ResourceParamRepository;
import it.woodcast.resources.*;
import it.woodcast.resources.csv.WorkingCalendarCsv;
import it.woodcast.services.CalendarService;
import it.woodcast.services.RateParmaServices;
import it.woodcast.services.ResourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
public class ResourcesFacade extends BaseFacade {
    @Autowired
    ResourceServices resourceServices;
    @Autowired
    RateParmaServices rateParmaServices;
    @Autowired
    ResourceParamRepository resourceParamRepository;

    @Autowired
    CalendarService calendarService;

    private static final BigDecimal ZERO = BigDecimal.ZERO;

    public ResourcesResponce findAllResources() {
        ResourcesResponce resourcesResponce = new ResourcesResponce();

        List<Resource> resources = new ArrayList<>();
        resourceServices.findAll().stream().forEach(resourceEntity -> {
            Resource resource = this.modelMapper.map(resourceEntity, Resource.class);
            resource.setGrade(resourceEntity.getRateParamEntity().getGrade());
            resource.setRate(resourceEntity.getRateParamEntity().getRate());
            resources.add(resource);
            resourcesResponce.setTotalCost(resourcesResponce.getTotalCost().add(resourceEntity.getRateParamEntity().getRate()));
        });
        BigDecimal medimCost = resourcesResponce.getTotalCost().divide(BigDecimal.valueOf(resources.size()), 2, RoundingMode.HALF_UP);
        resourcesResponce.setTotalCost(medimCost);
        resourcesResponce.setResources(resources);

        resourcesResponce.setTotalResources(resources.size());
        return resourcesResponce;
    }

    public ResourcesResponce findAllResourceCalendar(Integer idServiceRegistry, String typeSearch) {
        ResourcesResponce resourcesResponce = new ResourcesResponce();

        List<Resource> resources = new ArrayList<>();
        List<ResourceEntity> resourceEntities = new ArrayList<>();
        if ("NO_CALENDAR".equals(typeSearch)) {
            resourceEntities = resourceServices.findAllResourceNotCalendar(idServiceRegistry);
        } else {
            resourceEntities = resourceServices.findAllResourceCalendar(idServiceRegistry);
        }

        resourceEntities.stream().forEach(resourceEntity -> {
            Resource resource = this.modelMapper.map(resourceEntity, Resource.class);
            resource.setGrade(resourceEntity.getRateParamEntity().getGrade());
            resource.setRate(resourceEntity.getRateParamEntity().getRate());
            resources.add(resource);
            resourcesResponce.setTotalCost(resourcesResponce.getTotalCost().add(resourceEntity.getRateParamEntity().getRate()));
        });

        if (ZERO.compareTo(resourcesResponce.getTotalCost()) != 0) {
            BigDecimal medimCost = resourcesResponce.getTotalCost().divide(BigDecimal.valueOf(resources.size()), 2, RoundingMode.HALF_UP);
            resourcesResponce.setTotalCost(medimCost);
        }


        resourcesResponce.setResources(resources);

        resourcesResponce.setTotalResources(resources.size());
        return resourcesResponce;
    }

    public Integer save(Resource resourceResource) {
        ResourceEntity resourceEntity = this.modelMapper.map(resourceResource, ResourceEntity.class);
        resourceEntity.setRateParamEntity(rateParmaServices.findByGrade(resourceResource.getGrade()));

        Integer id = resourceServices.save(resourceEntity);
        return id;
    }

    public List<RateParamResource> findAllResourceParam() {
        List<RateParamResource> rateParamResources = new ArrayList<>();
        resourceParamRepository.findAllByOrderByGradeAsc().stream().forEach(rateParamEntity -> {
            RateParamResource rateParamResource = this.modelMapper.map(rateParamEntity, RateParamResource.class);
            rateParamResources.add(rateParamResource);
        });

        return rateParamResources;

    }

    public void saveAllVacation(List<WorkingCalendarCsv> workingCalendarCsvs) {
        List<ResourcesVacationEntity> resourcesVacationEntities = new ArrayList<>();
        workingCalendarCsvs.stream().forEach(workingCalendarCsv -> {
            ResourcesVacationEntity resourcesVacationEntity = this.modelMapper.map(workingCalendarCsv, ResourcesVacationEntity.class);
            ResourcesVacationId resourcesVacationId = new ResourcesVacationId();
            resourcesVacationId.setResourceFiscalCode(workingCalendarCsv.getResourceFiscalCode());
            resourcesVacationId.setYear(workingCalendarCsv.getYear());
            resourcesVacationEntity.setResourcesVacationId(resourcesVacationId);
            resourcesVacationEntities.add(resourcesVacationEntity);
        });
        resourceServices.saveAllVacation(resourcesVacationEntities);
    }

    public List<ResourceViewResource> findAllResourceView() {
        List<ResourceViewResource> resourceViewResources = new ArrayList<>();
        resourceServices.findAllResourceView().stream().forEach(resourceViewEntity -> {
            ResourceViewResource resourceViewResource = this.modelMapper.map(resourceViewEntity, ResourceViewResource.class);
            evaluateWorkableDaysAndStoplight(resourceViewEntity, resourceViewResource);
            resourceViewResources.add(resourceViewResource);
        });
        return resourceViewResources;
    }

    public List<ResourceAllocationMonth> findByMonthAndResouceId(Integer month, Integer resourceId) {
        List<ResourceAllocationMonth> resourceAllocationMonths = new ArrayList<>();
        calendarService.findByMonthAndResouceId(evaluateDate(month), resourceId).stream().forEach(calendarEntity -> {
            ResourceAllocationMonth resourceAllocationMonth = new ResourceAllocationMonth();
            resourceAllocationMonth.setNominative(calendarEntity.getResourceEntities().getNominative());
            resourceAllocationMonth.setBatchRegistryOrder(calendarEntity.getCustomerServiceEntities().getOrder());
            resourceAllocationMonth.setDays(calendarEntity.getWorkingDay());
            resourceAllocationMonth.setBatchRegistryDescription(calendarEntity.getCustomerServiceEntities().getDescription());
            resourceAllocationMonths.add(resourceAllocationMonth);
        });
        return resourceAllocationMonths;
    }

    private Date evaluateDate(Integer month) {
        int year = 2024;
        Date firstDayOfMonth = null;

        switch (month.intValue()) {
            case 1:
                firstDayOfMonth = Date.valueOf(year + "-01-01");
                break;
            case 2:
                firstDayOfMonth = Date.valueOf(year + "-02-01");
                break;
            case 3:
                firstDayOfMonth = Date.valueOf(year + "-03-01");
                break;
            case 4:
                firstDayOfMonth = Date.valueOf(year + "-04-01");
                break;
            case 5:
                firstDayOfMonth = Date.valueOf(year + "-05-01");
                break;
            case 6:
                firstDayOfMonth = Date.valueOf(year + "-06-01");
                break;
            case 7:
                firstDayOfMonth = Date.valueOf(year + "-07-01");
                break;
            case 8:
                firstDayOfMonth = Date.valueOf(year + "-08-01");
                break;
            case 9:
                firstDayOfMonth = Date.valueOf(year + "-09-01");
                break;
            case 10:
                firstDayOfMonth = Date.valueOf(year + "-10-01");
                break;
            case 11:
                firstDayOfMonth = Date.valueOf(year + "-11-01");
                break;
            case 12:
                firstDayOfMonth = Date.valueOf(year + "-12-01");
                break;
        }
        return firstDayOfMonth;
    }

    private void evaluateWorkableDaysAndStoplight(ResourceViewEntity resourceViewEntity, ResourceViewResource resourceViewResource) {

        resourceViewResource.setJanuaryWorkableDays(resourceViewEntity.getJanuaryWc().subtract(resourceViewEntity.getRvJanuary()));
        resourceViewResource.setStopLJanuary(evaluateStopLight(resourceViewResource.getJanuaryWorkableDays(), resourceViewResource.getJanuary()));

        resourceViewResource.setFebruaryWorkableDays(resourceViewEntity.getFebruaryWc().subtract(resourceViewEntity.getRvFebruary()));
        resourceViewResource.setStopLFebruary(evaluateStopLight(resourceViewResource.getFebruaryWorkableDays(), resourceViewResource.getFebruary()));

        resourceViewResource.setMarchWorkableDays(resourceViewEntity.getMarchWc().subtract(resourceViewEntity.getRvMarch()));
        resourceViewResource.setStopLMarch(evaluateStopLight(resourceViewResource.getMarchWorkableDays(), resourceViewResource.getMarch()));

        resourceViewResource.setAprilWorkableDays(resourceViewEntity.getAprilWc().subtract(resourceViewEntity.getRvApril()));
        resourceViewResource.setStopLApril(evaluateStopLight(resourceViewResource.getAprilWorkableDays(), resourceViewResource.getApril()));

        resourceViewResource.setMayWorkableDays(resourceViewEntity.getMayWc().subtract(resourceViewEntity.getRvMay()));
        resourceViewResource.setStopLMay(evaluateStopLight(resourceViewResource.getMayWorkableDays(), resourceViewResource.getMay()));

        resourceViewResource.setJuneWorkableDays(resourceViewEntity.getJuneWc().subtract(resourceViewEntity.getRvJune()));
        resourceViewResource.setStopLJune(evaluateStopLight(resourceViewResource.getJuneWorkableDays(), resourceViewResource.getJune()));

        resourceViewResource.setJulyWorkableDays(resourceViewEntity.getJulyWc().subtract(resourceViewEntity.getRvJuly()));
        resourceViewResource.setStopLJuly(evaluateStopLight(resourceViewResource.getJulyWorkableDays(), resourceViewResource.getJuly()));

        resourceViewResource.setAugustWorkableDays(resourceViewEntity.getAugustWc().subtract(resourceViewEntity.getRvAugust()));
        resourceViewResource.setStopLAugust(evaluateStopLight(resourceViewResource.getAugustWorkableDays(), resourceViewResource.getAugust()));

        resourceViewResource.setSeptemberWorkableDays(resourceViewEntity.getSeptemberWc().subtract(resourceViewEntity.getRvSeptember()));
        resourceViewResource.setStopLSeptember(evaluateStopLight(resourceViewResource.getSeptemberWorkableDays(), resourceViewResource.getSeptember()));

        resourceViewResource.setOctoberWorkableDays(resourceViewEntity.getOctoberWc().subtract(resourceViewEntity.getRvOctober()));
        resourceViewResource.setStopLOctober(evaluateStopLight(resourceViewResource.getOctoberWorkableDays(), resourceViewResource.getOctober()));

        resourceViewResource.setNovemberWorkableDays(resourceViewEntity.getNovemberWc().subtract(resourceViewEntity.getRvNovember()));
        resourceViewResource.setStopLNovember(evaluateStopLight(resourceViewResource.getNovemberWorkableDays(), resourceViewResource.getNovember()));

        resourceViewResource.setDecemberWorkableDays(resourceViewEntity.getDecemberWc().subtract(resourceViewEntity.getRvDecember()));
        resourceViewResource.setStopLDecember(evaluateStopLight(resourceViewResource.getDecemberWorkableDays(), resourceViewResource.getDecember()));

    }

    private StoplightEnum evaluateStopLight(BigDecimal workableDays, BigDecimal workingDays) {
        if (workableDays.compareTo(workingDays) == 0) {
            return StoplightEnum.GREEN;
        } else if (workableDays.compareTo(workingDays) >= 1) {
            return StoplightEnum.YELLOW;
        } else {
            return StoplightEnum.RED;
        }

    }

}
