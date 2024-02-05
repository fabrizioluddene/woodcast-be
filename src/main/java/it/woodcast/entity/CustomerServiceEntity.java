package it.woodcast.entity;

import it.woodcast.resources.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customer_serivce")
public class CustomerServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cs_id",insertable=false, updatable=false)

    private Integer id;
    @Column(name = "cs_service_name")
    private String name;
    @Column(name = "cs_rate")
    private BigDecimal rate;

    @ManyToOne
    @JoinColumn( referencedColumnName = "c_id")
    private CustomerEntity customer;



}
