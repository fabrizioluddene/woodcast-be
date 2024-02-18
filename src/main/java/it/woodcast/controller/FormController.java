package it.woodcast.controller;

import it.woodcast.entity.FormEntity;
import it.woodcast.entity.RulesEntity;
import it.woodcast.repository.EnumerationRepository;
import it.woodcast.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/form-generator")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FormController {

    @Autowired
    FormRepository enumerationRepository;
    @GetMapping("/{formName}")
    public ResponseEntity<FormEntity> findall(@PathVariable String formName) {
        return ResponseEntity.ok(enumerationRepository.findByFormName(formName));
    }
    @PostMapping("/")
    public ResponseEntity<FormEntity> save(@RequestBody FormEntity resource) {
        return ResponseEntity.ok(enumerationRepository.save(resource));
    }
}
