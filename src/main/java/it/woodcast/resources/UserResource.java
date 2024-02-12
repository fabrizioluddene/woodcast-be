package it.woodcast.resources;

import it.woodcast.entity.RulesEntity;
import it.woodcast.enumeration.RulesEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Base64;
import java.util.List;

@Getter
@Setter
public class UserResource {

    private Integer id;
    private String name;
    private String surname;
    private String username;
    private String password;

    List<RulesEnum> rules;

    public void setPassword(String password){
        this.password = Base64.getEncoder().encodeToString(password.getBytes());
    }

}
