package it.woodcast.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "woodcast_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "us_id")
    private Integer id;

    @Column(name = "us_name")
    private String name;

    @Column(name = "us_sername")
    private String surname;
    @Column(name = "us_username",unique = true)
    private String username;
    @Column(name = "us_password")
    private String password;



    @ManyToMany
    List<RulesEntity> rules;

}
