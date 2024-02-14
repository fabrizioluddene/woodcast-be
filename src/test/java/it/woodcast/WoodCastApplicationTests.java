package it.woodcast;

import it.woodcast.entity.BatchRegistryEntity;
import it.woodcast.repository.BatchRegistryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WoodCastApplicationTests {

    @Autowired
    BatchRegistryRepository batchRegistryRepository;

    @Test
    void contextLoads() {

        /*List<BatchRegistryEntity> batchRegistryEntity = batchRegistryRepository.findByCustomerAndIdAndUserId("1", "9", "1");

        System.out.println(batchRegistryEntity);*/
    }

}
