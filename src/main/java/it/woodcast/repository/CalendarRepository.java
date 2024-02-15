package it.woodcast.repository;

import it.woodcast.entity.BatchRegistryEntity;
import it.woodcast.entity.CalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<CalendarEntity, Integer> {

    List<CalendarEntity> getByCustomerServiceEntitiesOrderByMonth(BatchRegistryEntity customerServiceEntity);

    @Transactional
    @Modifying
    @Query("DELETE FROM CalendarEntity ce where ce.customerServiceEntities.id = :id")
    void deleteAllByCustomer(@Param("id") Integer id);

    @Query("select ce FROM CalendarEntity ce where ce.customerServiceEntities.id in :id")
    List<CalendarEntity> findByBatch(List<Integer> id);
}
