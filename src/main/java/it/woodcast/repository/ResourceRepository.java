package it.woodcast.repository;

import it.woodcast.entity.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResourceRepository extends JpaRepository<ResourceEntity, Integer> {


    @Query("SELECT ce.resourceEntities FROM CalendarEntity ce WHERE  ce.customerServiceEntities.id = :idServiceRegistry")
    List<ResourceEntity> findAllResourceCalendar(@Param("idServiceRegistry") Optional<Integer> idServiceRegistry);

    @Query("SELECT re FROM ResourceEntity re WHERE re.id NOT IN (SELECT DISTINCT ce.resourceEntities.id FROM CalendarEntity ce WHERE ce.customerServiceEntities.id = :idServiceRegistry)")
    List<ResourceEntity> findAllResourceNotCalendar(@Param("idServiceRegistry") Optional<Integer> idServiceRegistry);
}


