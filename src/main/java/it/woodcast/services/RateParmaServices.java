package it.woodcast.services;

import it.woodcast.entity.RateParamEntity;
import it.woodcast.entity.ResourceEntity;
import it.woodcast.repository.RateParmaRepository;
import it.woodcast.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateParmaServices {
    @Autowired
    private RateParmaRepository rateParmaRepository;
    public RateParamEntity findByGrade(String grade) {

        return rateParmaRepository.findByGrade(grade);
    }

}
