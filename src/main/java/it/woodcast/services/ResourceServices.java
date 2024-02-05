package it.woodcast.services;

import it.woodcast.entity.BatchRegistryEntity;
import it.woodcast.entity.ResourceEntity;
import it.woodcast.repository.BatchRegistryRepository;
import it.woodcast.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServices {
    @Autowired
    private ResourceRepository resourceRepository;
    public List<ResourceEntity> findAll() {


        return resourceRepository.findAll();
    }
    public Integer save(ResourceEntity resourceEntity) {

        ResourceEntity resourceEntityRet = resourceRepository.save(resourceEntity);
        return resourceEntityRet.getId();
    }
}
