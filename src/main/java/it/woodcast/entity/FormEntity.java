package it.woodcast.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "form_generator")
public class FormEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_id",nullable=false,insertable=false, updatable=false)
    private Integer id;
    @Column(name = "f_form_name")
    private String formName;
    @Column(name = "f_form_json")
    private String json;
}
