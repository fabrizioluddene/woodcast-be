package it.woodcast.services;

import it.woodcast.entity.BatchRegistryEntity;
import it.woodcast.entity.ResourceEntity;
import it.woodcast.repository.BatchRegistryRepository;
import it.woodcast.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceServices {
    @Autowired
    private ResourceRepository resourceRepository;
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
}
