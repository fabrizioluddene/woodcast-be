package it.woodcast.resources;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CalendarSaveResurce {
    private Integer calendarId;
    private Integer resourceId;
    private BigDecimal workingDay;
}
