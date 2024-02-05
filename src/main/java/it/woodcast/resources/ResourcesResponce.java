package it.woodcast.resources;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
public class ResourcesResponce {

    Integer totalResources;
    BigDecimal totalCost = BigDecimal.ZERO;
    List<Resource> resources;
}
