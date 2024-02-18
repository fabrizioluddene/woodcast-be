package it.woodcast.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.View;


import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "working_calendar")
public class WorkingCalendarEntity {
    @Id
    private BigDecimal year;

    private BigDecimal january;

    private BigDecimal february;

    private BigDecimal march;

    private BigDecimal april;

    private BigDecimal may;

    private BigDecimal june;

    private BigDecimal july;

    private BigDecimal august;

    private BigDecimal september;

    private BigDecimal october;

    private BigDecimal november;
    
    private BigDecimal december;


}
