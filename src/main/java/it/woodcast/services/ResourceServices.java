package it.woodcast.services;

import it.woodcast.entity.ResourceEntity;
import it.woodcast.entity.ResourceViewEntity;
import it.woodcast.entity.ResourcesVacationEntity;
import it.woodcast.repository.ResourceRepository;
import it.woodcast.repository.ResourceViewRepository;
import it.woodcast.repository.ResourcesVacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceServices {
    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    ResourceViewRepository resourceViewRepository;
    @Autowired
    ResourcesVacationRepository resourcesVacationRepository;


    public List<ResourceEntity> findAll() {


        return resourceRepository.findAll();
    }
    public List<ResourceEntity> findAllResourceCalendar(Integer idServiceRegistry) {


        return resourceRepository.findAllResourceCalendar(Optional.of(idServiceRegistry));
    }
    public List<ResourceEntity> findAllResourceNotCalendar(Integer idServiceRegistry) {


        return resourceRepository.findAllResourceNotCalendar(Optional.of(idServiceRegistry));
    }


    public Integer save(ResourceEntity resourceEntity) {

        ResourceEntity resourceEntityRet = resourceRepository.save(resourceEntity);
        return resourceEntityRet.getId();
    }

    public void saveAllVacation(List<ResourcesVacationEntity> resourcesVacationEntities){

        resourcesVacationRepository.saveAll(resourcesVacationEntities);
    }
    public void deleteAllVacatio(List<ResourcesVacationEntity> resourcesVacationEntities){

        resourcesVacationRepository.deleteAll(resourcesVacationEntities);
    }


    public List<ResourceViewEntity> findAllResourceView(){
        return resourceViewRepository.findAll();
    }
}
