package it.woodcast.controller;

import it.woodcast.logic.LoginFacade;
import it.woodcast.resources.BatchRegistry;
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

    @PostMapping("/login")
    public ResponseEntity<UserResource> login(@RequestBody UserResource userResource) {
        return ResponseEntity.ok(loginFacade.login(userResource.getPassword(),userResource.getUsername()));
    }

    @PostMapping("/")
    public ResponseEntity<UserResource> save(@RequestBody UserResource userResource) {
        return ResponseEntity.ok(loginFacade.save(userResource));
    }
}
