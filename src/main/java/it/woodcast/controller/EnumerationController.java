package it.woodcast.controller;

import it.woodcast.entity.RulesEntity;
import it.woodcast.repository.EnumerationRepository;
import it.woodcast.resources.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enum")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EnumerationController {

    @Autowired
    EnumerationRepository enumerationRepository;
    @GetMapping("/")
    public ResponseEntity<List<RulesEntity>> findall() {
        return ResponseEntity.ok(enumerationRepository.findAll());
    }
    @PostMapping("/")
    public ResponseEntity<RulesEntity> save(@RequestBody RulesEntity resource) {
        return ResponseEntity.ok(enumerationRepository.save(resource));
    }
}
