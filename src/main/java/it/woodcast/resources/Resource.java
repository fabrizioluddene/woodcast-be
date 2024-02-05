package it.woodcast.resources;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class Resource {
    private Integer id;
    private String nominative;
    private String company;
    private String area;
    private String number;
    private String grade;
    private BigDecimal rate;

}
