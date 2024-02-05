package it.woodcast.repository;

import it.woodcast.entity.BatchRegistryEntity;
import it.woodcast.entity.RateParamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateParmaRepository extends JpaRepository<RateParamEntity, Integer> {
    public RateParamEntity findByGrade(String grade);

}
