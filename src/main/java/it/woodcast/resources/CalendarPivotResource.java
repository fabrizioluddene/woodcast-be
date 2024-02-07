package it.woodcast.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class CalendarPivotResource extends Resource {

    private String batchRegistryName;

    private Map<String, Pivot> pivot= new HashMap<>();







}
