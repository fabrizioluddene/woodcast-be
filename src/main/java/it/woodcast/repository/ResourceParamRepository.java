package it.woodcast.repository;

import it.woodcast.entity.RateParamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceParamRepository extends JpaRepository<RateParamEntity, Integer> {

    public List<RateParamEntity> findAllByOrderByGradeAsc();

}
