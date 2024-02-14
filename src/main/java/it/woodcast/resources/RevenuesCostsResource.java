package it.woodcast.resources;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RevenuesCostsResource {
    private BigDecimal costs = BigDecimal.ZERO;
    private BigDecimal revenues =  BigDecimal.ZERO;
    private BigDecimal expectingPreceed = BigDecimal.ZERO;
    private StoplightEnum stoplight;
}
