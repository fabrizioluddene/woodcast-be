package it.woodcast.resources;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
public class ForecastResources extends Resource{

    Map<String, BigDecimal> calendar;
}
