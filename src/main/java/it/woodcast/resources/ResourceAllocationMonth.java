package it.woodcast.resources;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ResourceAllocationMonth {
    private String nominative;
    private BigDecimal days;
    private BigDecimal deltaDays;
    private String batchRegistryOrder;
    private String batchRegistryDescription;
}
