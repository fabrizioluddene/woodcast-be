package it.woodcast.entity;

import it.woodcast.enumeration.RulesEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rules")
public class RulesEntity {


    @Id
    @Column(name = "ru_rules")
    private RulesEnum rules;
}
