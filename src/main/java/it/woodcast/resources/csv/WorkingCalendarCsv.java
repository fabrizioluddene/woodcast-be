package it.woodcast.resources.csv;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WorkingCalendarCsv {

    @CsvBindByName(column = "year", required =   false)
    private BigDecimal year;
    @CsvBindByName(column = "january", required =   false)
    private BigDecimal january;
    @CsvBindByName(column = "february", required =   false)
    private BigDecimal february;
    @CsvBindByName(column = "march", required =   false)
    private BigDecimal march;
    @CsvBindByName(column = "april", required =   false)
    private BigDecimal april;
    @CsvBindByName(column = "may", required =   false)
    private BigDecimal may;
    @CsvBindByName(column = "june", required =   false)
    private BigDecimal june;
    @CsvBindByName(column = "july", required =   false)
    private BigDecimal july;
    @CsvBindByName(column = "august", required =   false)
    private BigDecimal august;
    @CsvBindByName(column = "september", required =   false)
    private BigDecimal september;
    @CsvBindByName(column = "october", required =   false)
    private BigDecimal october;
    @CsvBindByName(column = "november", required =   false)
    private BigDecimal november;
    @CsvBindByName(column = "december", required =   false)
    private BigDecimal december;
    @CsvBindByName(column = "fiscal_code",required =   true)
    private String resourceFiscalCode;
}
