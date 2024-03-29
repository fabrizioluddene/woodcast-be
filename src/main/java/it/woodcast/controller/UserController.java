package it.woodcast.controller;

import it.woodcast.enumeration.RulesEnum;
import it.woodcast.logic.LoginFacade;
import it.woodcast.logic.UserFacade;
import it.woodcast.resources.BatchRegistry;
import it.woodcast.resources.Customer;
import it.woodcast.resources.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    @Autowired
    private LoginFacade loginFacade;
    @Autowired
    private UserFacade userFacade;

    @PostMapping("/login")
    public ResponseEntity<UserResource> login(@RequestBody UserResource userResource) {
        return ResponseEntity.ok(loginFacade.login(userResource.getPassword(),userResource.getUsername()));
    }

    @PostMapping("/save")
    public ResponseEntity<UserResource> save(@RequestBody UserResource userResource) {
        return ResponseEntity.ok(loginFacade.save(userResource));
    }

    @GetMapping("/rule/{rules}")
    public ResponseEntity<List<UserResource>> findAllByRules(@PathVariable RulesEnum rules) {
        return ResponseEntity.ok(userFacade.findAllByRules(rules));
    }
}
