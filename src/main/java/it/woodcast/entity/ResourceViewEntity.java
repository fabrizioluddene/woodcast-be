package it.woodcast.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class ResourceViewEntity {
    @Id
    private Integer id;
    private Integer resourceId;
    private String resourceName;

    private BigDecimal january;
    private BigDecimal januaryWc;
    private BigDecimal rvJanuary;
    private BigDecimal february;
    private BigDecimal februaryWc;
    private BigDecimal rvFebruary;
    private BigDecimal march;
    private BigDecimal marchWc;
    private BigDecimal rvMarch;
    private BigDecimal april;
    private BigDecimal aprilWc;
    private BigDecimal rvApril;
    private BigDecimal may;
    private BigDecimal mayWc;
    private BigDecimal rvMay;
    private BigDecimal june;
    private BigDecimal juneWc;
    private BigDecimal rvJune;
    private BigDecimal july;
    private BigDecimal julyWc;
    private BigDecimal rvJuly;
    private BigDecimal august;
    private BigDecimal augustWc;
    private BigDecimal rvAugust;
    private BigDecimal september;
    private BigDecimal septemberWc;
    private BigDecimal rvSeptember;
    private BigDecimal october;
    private BigDecimal octoberWc;
    private BigDecimal rvOctober;
    private BigDecimal november;
    private BigDecimal novemberWc;
    private BigDecimal rvNovember;
    private BigDecimal december;
    private BigDecimal decemberWc;
    private BigDecimal rvDecember;



}
