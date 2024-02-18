package it.woodcast.repository;

import it.woodcast.entity.UserEntity;
import it.woodcast.entity.WorkingCalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface WorkingCalendarRepository extends JpaRepository<WorkingCalendarEntity, BigDecimal> {

    WorkingCalendarEntity findByYear(BigDecimal year);

}
