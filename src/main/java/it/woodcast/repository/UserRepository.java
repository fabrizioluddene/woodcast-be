package it.woodcast.repository;

import it.woodcast.entity.RulesEntity;
import it.woodcast.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    public UserEntity findByPasswordAndUsername(String password,String username);

    public List<UserEntity> findByRules(RulesEntity rulesEntity);
}
