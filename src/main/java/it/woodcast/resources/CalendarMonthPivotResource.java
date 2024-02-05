package it.woodcast.resources;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CalendarMonthPivotResource {
    private Integer id;
    private BigDecimal workingDay;
}
