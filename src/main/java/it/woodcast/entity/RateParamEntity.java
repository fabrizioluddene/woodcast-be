package it.woodcast.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "resources_rate_param")
public class RateParamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rrp_id")
    private Integer id;
    @Column(name = "rrp_grade")
    private String grade;
    @Column(name = "rrp_rate")
    private BigDecimal rate;


}
