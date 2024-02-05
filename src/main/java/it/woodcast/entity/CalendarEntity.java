package it.woodcast.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name="calendar")
@Getter
@Setter
public class CalendarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id",nullable=false,insertable=false, updatable=false)
    private Integer id;

    @Column(name="month")
    private Date month;
    @Column(name="working_day",columnDefinition = "default '0.00'")
    private BigDecimal workingDay;

    @ManyToOne
    private ResourceEntity resourceEntities;
    @ManyToOne
    private CustomerServiceEntity customerServiceEntities;


}
