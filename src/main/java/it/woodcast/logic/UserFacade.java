package it.woodcast.logic;

import it.woodcast.enumeration.RulesEnum;
import it.woodcast.resources.UserResource;
import it.woodcast.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserFacade extends BaseFacade{
    @Autowired
    private UserService userService;
    public List<UserResource> findAllByRules(RulesEnum rulesEnum){
        List<UserResource> userResources = new ArrayList<>();
        userService.findByRules(rulesEnum).stream().forEach(r->{
            UserResource userResource=  modelMapper.map(r,UserResource.class);
            userResource.setPassword("");
            List<RulesEnum> rules = new ArrayList<>();
            r.getRules().stream().forEach(rulesEntity -> {
                rules.add(rulesEntity.getRules());
            });
            userResource.setRules(rules);
            userResources.add(userResource);
        });
        return userResources;

    }
}
