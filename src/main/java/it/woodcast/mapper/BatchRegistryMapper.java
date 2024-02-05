package it.woodcast.mapper;

import it.woodcast.entity.BatchRegistryEntity;
import it.woodcast.resources.BatchRegistry;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BatchRegistryMapper {
    private ModelMapper modelMapper = new ModelMapper();

    public List<BatchRegistry> mapAsList(List<BatchRegistryEntity> batchRegistryEntities) {
        this. modelMapper = new ModelMapper();

        return batchRegistryEntities.stream().map(inputCallMap -> modelMapper.map(inputCallMap, BatchRegistry.class)).collect(Collectors.toList());

    }
    public BatchRegistry mapAtoB(BatchRegistryEntity batchRegistryEntity){
        return this. modelMapper.map(batchRegistryEntity, BatchRegistry.class);
    }
}
