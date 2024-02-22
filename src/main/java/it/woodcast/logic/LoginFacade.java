package it.woodcast.logic;

import it.woodcast.entity.RulesEntity;
import it.woodcast.entity.UserEntity;
import it.woodcast.enumeration.RulesEnum;
import it.woodcast.resources.UserResource;
import it.woodcast.services.JwtTokenProvider;
import it.woodcast.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoginFacade extends BaseFacade {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserService userService;

    public UserResource login( String password,String username) {
        UserEntity userEntity = userService.getUserByPasswordAndUsername(password, username);
        UserResource userResource;
        if (userEntity != null){
            userResource = modelMapper.map(userEntity, UserResource.class);
            userResource.setRules(setRules(userEntity));

            userResource.setPassword("");
            userResource.setJwt(jwtTokenProvider.generateToken(userResource));
            userResource.setLogged(true);
        }else{
            userResource = new UserResource();
            userResource.setLogged(false);
        }

        return userResource;

    }
    private List<RulesEnum> setRules(UserEntity userEntity) {
        return userEntity.getRules().stream()
                .map(rule -> rule.getRules())
                .collect(Collectors.toList());
    }

    public UserResource save(UserResource userResource) {
        UserEntity userEntity = modelMapper.map(userResource, UserEntity.class);
        userEntity.setRules(setRules(userResource));
        UserEntity savedUserEntity = userService.save(userEntity);
        return modelMapper.map(savedUserEntity, UserResource.class);
    }

    private List<RulesEntity> setRules(UserResource userResource) {
        if (userResource.getRules()!=null){
            return userResource.getRules().stream()
                    .map(rule -> new RulesEntity(rule))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();

    }
}
