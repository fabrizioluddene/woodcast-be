package it.woodcast.controller;

import it.woodcast.logic.ResourcesFacade;
import it.woodcast.resources.RateParamResource;
import it.woodcast.resources.Resource;
import it.woodcast.resources.ResourcesResponce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resource")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ResourcesController {
    @Autowired
    private ResourcesFacade resourcesFacade;

    @GetMapping("find-all")
    public ResponseEntity<ResourcesResponce> findAll() {
        return ResponseEntity.ok(resourcesFacade.findAllResources());
    }

    @PostMapping("save")
    public ResponseEntity<Integer> save(@RequestBody Resource resource) {
        return ResponseEntity.ok(resourcesFacade.save(resource));
    }

    @GetMapping("rate-param/find-all")
    public ResponseEntity<List<RateParamResource>> findAllResourceParam() {
        return ResponseEntity.ok(resourcesFacade.findAllResourceParam());
    }


}
