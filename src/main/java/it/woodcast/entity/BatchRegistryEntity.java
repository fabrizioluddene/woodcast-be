package it.woodcast.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "batch_registry")
public class BatchRegistryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "br_id")
    private Integer id;
    @Column(name = "br_order")
    private String order;
    @Column(name = "br_description")
    private String description;
    @Column(name = "br_pm")
    private String pm;
    @Column(name = "br_order_type")
    private Character orderType;
    @Column(name = "br_order_status")
    private Character orderStatus;
    @Column(name = "br_proceeds")
    private BigDecimal proceeds;
    @Column(name = "br_note")
    private String note;
    @Column(name = "br_proceeds_day_plafond")
    private BigDecimal proceedsDayPlafond;
    @Column(name = "br_days_remaining")
    private BigDecimal daysRemaining;
    @Column(name = "br_expected_margin")
    private BigDecimal expectedMargin;
    @Column(name = "br_vendor_rate")
    private BigDecimal vendorRate;
    @ManyToOne
    private CustomerEntity customer;
    @ManyToMany
    private List<UserEntity> user;


}
