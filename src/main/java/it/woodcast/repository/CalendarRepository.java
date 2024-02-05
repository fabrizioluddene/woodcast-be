package it.woodcast.repository;

import it.woodcast.entity.CalendarEntity;
import it.woodcast.entity.CustomerServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<CalendarEntity,Integer> {

    List<CalendarEntity> getByCustomerServiceEntitiesOrderByMonth(CustomerServiceEntity customerServiceEntity);
}
