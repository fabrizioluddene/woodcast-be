package it.woodcast.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "resources_vacation")
public class ResourcesVacationEntity {

    @EmbeddedId
    private ResourcesVacationId resourcesVacationId;


    @Column(name = "rv_january")
    private BigDecimal january;
    @Column(name = "rv_february")
    private BigDecimal february;
    @Column(name = "rv_march")
    private BigDecimal march;
    @Column(name = "rv_april")
    private BigDecimal april;
    @Column(name = "rv_may")
    private BigDecimal may;
    @Column(name = "rv_june")
    private BigDecimal june;
    @Column(name = "rv_july")
    private BigDecimal july;
    @Column(name = "rv_august")
    private BigDecimal august;
    @Column(name = "rv_september")
    private BigDecimal september;
    @Column(name = "rv_october")
    private BigDecimal october;
    @Column(name = "rv_november")
    private BigDecimal november;
    @Column(name = "rv_december")
    private BigDecimal december;

}
