package it.woodcast.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@Embeddable
public class ResourcesVacationId {


    @Column(name = "rv_year")
    private BigDecimal year;

    @Column(name = "rv_fiscal_code")
    private String resourceFiscalCode;
}
