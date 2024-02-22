package it.woodcast.resources;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class ResourceViewResource {

  
    private Integer id;
    private Integer resourceId;
    private String resourceName;

    private BigDecimal january;
    private BigDecimal januaryWorkableDays;
    private StoplightEnum stopLJanuary;
    private BigDecimal januaryDelta;

    private BigDecimal february;
    private BigDecimal februaryWorkableDays;
    private StoplightEnum  stopLFebruary;
    private BigDecimal februaryDelta;

    private BigDecimal march;
    private BigDecimal marchWorkableDays;
    private StoplightEnum  stopLMarch;
    private BigDecimal marchDelta;

    private BigDecimal april;
    private BigDecimal aprilWorkableDays;
    private StoplightEnum  stopLApril;

    private BigDecimal may;
    private BigDecimal mayWorkableDays;
    private StoplightEnum  stopLMay;

    private BigDecimal june;
    private BigDecimal juneWorkableDays;
    private StoplightEnum  stopLJune;

    private BigDecimal july;
    private BigDecimal julyWorkableDays;
    private StoplightEnum  stopLJuly;

    private BigDecimal august;
    private BigDecimal augustWorkableDays;
    private StoplightEnum  stopLAugust;

    private BigDecimal september;
    private BigDecimal septemberWorkableDays;
    private StoplightEnum  stopLSeptember;

    private BigDecimal october;
    private BigDecimal octoberWorkableDays;
    private StoplightEnum  stopLOctober;

    private BigDecimal november;
    private BigDecimal novemberWorkableDays;
    private StoplightEnum  stopLNovember;

    private BigDecimal december;
    private BigDecimal decemberWorkableDays;
    private StoplightEnum  stopLDecember;
}
