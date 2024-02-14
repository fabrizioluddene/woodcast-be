package it.woodcast.services;

import it.woodcast.entity.UserEntity;
import it.woodcast.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserEntity getUserByPasswordAndUsername(String password, String username) {
        return userRepository.findByPasswordAndUsername(password, username);
    }
    public UserEntity save(UserEntity userEntity){
        return userRepository.save(userEntity);
    }


}
