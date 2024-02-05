package it.woodcast.controller;

import it.woodcast.logic.BatchRegistryFacade;
import it.woodcast.resources.BatchRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/batch-registry")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BatchRegistryController {
    @Autowired
    private BatchRegistryFacade batchRegistryFacade;

    @GetMapping("find-all")
    public ResponseEntity<List<BatchRegistry>> findAll() {
        return ResponseEntity.ok(batchRegistryFacade.getBatchRegistryResources());
    }

    @PostMapping("save")
    public ResponseEntity<String> save(@RequestBody BatchRegistry batchRegistry) {
        batchRegistryFacade.save(batchRegistry);
        return ResponseEntity.ok("ok");
    }
}
