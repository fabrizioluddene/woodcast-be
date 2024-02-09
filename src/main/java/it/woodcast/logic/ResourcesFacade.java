package it.woodcast.logic;

import it.woodcast.entity.ResourceEntity;
import it.woodcast.repository.ResourceParamRepository;
import it.woodcast.resources.RateParamResource;
import it.woodcast.resources.Resource;
import it.woodcast.resources.ResourcesResponce;
import it.woodcast.services.RateParmaServices;
import it.woodcast.services.ResourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResourcesFacade extends BaseFacade {
    @Autowired
    ResourceServices resourceServices;
    @Autowired
    RateParmaServices rateParmaServices;
    @Autowired
    ResourceParamRepository resourceParamRepository;

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


}
