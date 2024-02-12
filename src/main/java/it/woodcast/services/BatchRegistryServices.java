package it.woodcast.services;

import it.woodcast.entity.BatchRegistryEntity;
import it.woodcast.repository.BatchRegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchRegistryServices {

    @Autowired
    private BatchRegistryRepository batchRegistryRepository;
    public List<BatchRegistryEntity> findAll() {
        return batchRegistryRepository.findAll();
    }
    public List<BatchRegistryEntity> findByCustomer(String id) {
        return batchRegistryRepository.findByCustomer(id);
    }
    public List<BatchRegistryEntity> findByCustomerAndId(String id,String idBatchRegistry) {
        return batchRegistryRepository.findByCustomerAndId(id,idBatchRegistry);
    }


    public void save(BatchRegistryEntity inputCallMap) {
        batchRegistryRepository.save(inputCallMap);
    }
    public void delete(Integer id){
        batchRegistryRepository.delete(batchRegistryRepository.findById(id).get());
    }
}
