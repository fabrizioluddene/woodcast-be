package it.woodcast.resources.dashboard.graph;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CalendarGraphDashbordResource {
    private List<BigDecimal> data;
    private String name;
    private String type= "area";
}
