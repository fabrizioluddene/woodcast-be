package it.woodcast.resources;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CalendarResurce {

    private List<Resource> resource;
    private BatchRegistry customerService;
}
