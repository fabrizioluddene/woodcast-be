package it.woodcast.resources;

import it.woodcast.enumeration.RulesEnum;
import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Getter
@Setter
public class JwtUser {
    private String username;
    private String userId;
    private List<RulesEnum> rules;

}
