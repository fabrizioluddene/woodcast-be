package it.woodcast.resources;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatchRegistry {
    private Integer id;
    private String order;
    private String description;
    private String pm;
    private char orderType;
    private char orderStatus;
    private BigDecimal proceeds;
    private String note;
    private Long proceedsDayPlafond;
    private BigDecimal proceedsPlafond;
    private Long daysRemaining;
    private BigDecimal proceedsRemaining;
    private BigDecimal overallCosts;
    private BigDecimal expectedMargin;
    private Integer idCustomerService;
    private String customerService;
    private BigDecimal expectedMarginEU;
    private BigDecimal effectiveCosts = BigDecimal.ZERO;
    private BigDecimal totalEffectiveDay = BigDecimal.ZERO;
    private BigDecimal averageRate= BigDecimal.ZERO;
    private BigDecimal calculateMargin= BigDecimal.ZERO;
    private BigDecimal effectiveMUP= BigDecimal.ZERO;
    private BigDecimal deltaEffectiveCost= BigDecimal.ZERO;
    private BigDecimal vendorRate;




}
