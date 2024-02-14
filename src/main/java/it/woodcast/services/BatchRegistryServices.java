package it.woodcast.services;

import it.woodcast.entity.BatchRegistryEntity;
import it.woodcast.entity.CustomerEntity;
import it.woodcast.repository.BatchRegistryRepository;
import it.woodcast.resources.JwtUser;
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

    public List<BatchRegistryEntity> findByCustomerAndIdAndUserId(String id, String idBatchRegistry, String userId) {
        return batchRegistryRepository.findByCustomerAndId(id,idBatchRegistry);
    }
    public List<BatchRegistryEntity> findByCustomerUserId(String id,  String userId) {
        return batchRegistryRepository.findByCustomerUserId(id,userId);
    }
    public void save(BatchRegistryEntity inputCallMap) {
        batchRegistryRepository.save(inputCallMap);
    }
    public void delete(Integer id){
        batchRegistryRepository.delete(batchRegistryRepository.findById(id).get());
    }

    public List<BatchRegistryEntity> findByCustomer(CustomerEntity id){
        return batchRegistryRepository.findByCustomer(id);
    }


    public BatchRegistryEntity findById(Integer id){
        return batchRegistryRepository.findById(id).get();
    }


}
