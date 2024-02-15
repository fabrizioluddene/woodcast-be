package it.woodcast.services;

import it.woodcast.entity.RulesEntity;
import it.woodcast.entity.UserEntity;
import it.woodcast.enumeration.RulesEnum;
import it.woodcast.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserEntity getUserByPasswordAndUsername(String password, String username) {
        return userRepository.findByPasswordAndUsername(password, username);
    }

    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public List<UserEntity> findByRules(RulesEnum rules) {
        RulesEntity rulesEntity = new RulesEntity();
        rulesEntity.setRules(RulesEnum.PROGRAM_MANAGER);
        return userRepository.findByRules(rulesEntity);

    }


}
