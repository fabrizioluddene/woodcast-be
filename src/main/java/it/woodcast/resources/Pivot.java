package it.woodcast.resources;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Pivot {
    private Integer idCalendar;
    private BigDecimal workingDay = BigDecimal.ZERO;
    private BigDecimal calculatedCost = BigDecimal.ZERO;
    private BigDecimal calculatedProceeds = BigDecimal.ZERO;


}
