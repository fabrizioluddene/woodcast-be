package it.woodcast.controller;

import it.woodcast.logic.ResourcesFacade;
import it.woodcast.resources.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

    @GetMapping("find-all/calendar/service-registry/{idServiceRegistry}")
    public ResponseEntity<ResourcesResponce> findAllResourceCalendar(@PathVariable Integer idServiceRegistry, @RequestParam("typeSearch") String typeSearch) {
        return ResponseEntity.ok(resourcesFacade.findAllResourceCalendar(idServiceRegistry, typeSearch));
    }

    @PostMapping("save")
    public ResponseEntity<Integer> save(@RequestBody Resource resource) {
        return ResponseEntity.ok(resourcesFacade.save(resource));
    }

    @GetMapping("rate-param/find-all")
    public ResponseEntity<List<RateParamResource>> findAllResourceParam() {
        return ResponseEntity.ok(resourcesFacade.findAllResourceParam());
    }

    @GetMapping("allocation")
    public ResponseEntity<List<ResourceViewResource>> findAllResourceView() {
        return ResponseEntity.ok(resourcesFacade.findAllResourceView());
    }
    @GetMapping("{idResource}/allocation/month/{month}")
    public ResponseEntity<List<ResourceAllocationMonth>> findByMonthAndResouceId(@PathVariable Integer idResource,@PathVariable Integer month) {
        return ResponseEntity.ok(resourcesFacade.findByMonthAndResouceId(month,idResource));
    }


}
