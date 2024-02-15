package it.woodcast.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "resources")
public class ResourceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_id",nullable=false,insertable=false, updatable=false)
    private Integer id;
    
    @Column(name = "r_nominative")
    private String nominative;

    @Column(name = "r_company")
    private String company;

    @Column(name = "r_area")
    private String area;

    @Column(name = "r_number")
    private String number;

    @Column(name = "r_cdc")
    private String cdc;

    @Column(name = "r_email")
    private String email;
    @Column(name = "r_fiscal_code")
    private String fiscalCode;

    @ManyToOne
    @JoinColumn(name = "r_rate_param_id", referencedColumnName = "rrp_id")
    private RateParamEntity rateParamEntity;
}
