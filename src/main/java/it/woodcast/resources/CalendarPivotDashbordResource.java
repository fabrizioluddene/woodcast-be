package it.woodcast.resources;

import it.woodcast.resources.dashboard.graph.CalendarGraphDashbordResource;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CalendarPivotDashbordResource {
    private String order;
    private Map<String, Pivot> pivot= new HashMap<>();


}
