package it.woodcast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.woodcast.entity.ResourceEntity;
import it.woodcast.repository.*;
import it.woodcast.services.RateParmaServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class WoodCastApplicationTests {

    @Autowired
    ResourceRepository userRepository;

    @Autowired
    ResourceParamRepository resourceParamRepository;

    @Autowired
    RateParmaServices rateParmaServices;

    @Autowired
    ResourceViewRepository resourceViewRepository;

    @Test
    void contextLoads() throws IOException {
        /*ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Carica il file JSON dalla cartella delle risorse
        Resource resource = new ClassPathResource("allDipendentiReactive.json");
        List<Dipendenti> dipendenti = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<Dipendenti>>() {});

        List<ResourceEntity> resourceEntities = new ArrayList<>();
       for(Dipendenti d :dipendenti  ){
            ResourceEntity resourceEntity = new ResourceEntity();
            resourceEntity.setCdc(d.getCdc());
            resourceEntity.setNominative(d.getNome());
            resourceEntity.setCompany(d.getAzienda());
            resourceEntity.setFiscalCode(d.getCodiceFiscale());
            resourceEntity.setEmail(d.getMail());
            resourceEntity.setArea(d.getCdc());
            resourceEntity.setRateParamEntity(rateParmaServices.findByGrade(d.getLivello()));
            resourceEntities.add(resourceEntity);

        }
        System.out.println("");
        userRepository.saveAll(resourceEntities);*/

        resourceViewRepository.findAll();
    }

}
