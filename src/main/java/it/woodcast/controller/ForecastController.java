package it.woodcast.controller;

import it.woodcast.logic.CalendarFacade;
import it.woodcast.logic.ForecastFacade;
import it.woodcast.resources.CalendarPivotResource;
import it.woodcast.resources.CalendarResurce;
import it.woodcast.resources.CalendarSaveResurce;
import it.woodcast.resources.ForecastResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/forecast")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ForecastController {
    @Autowired
    private ForecastFacade customerFacade;
    @Autowired
    private CalendarFacade calendarFacade;


    @GetMapping("/{customerId}")
    public ResponseEntity<List<CalendarPivotResource>> findAllBatchRegistry(@PathVariable String customerId) {

        return ResponseEntity.ok(customerFacade.getAllCustomerBatchRegistry(customerId));
    }
    @PostMapping("/create")
    public ResponseEntity<List<CalendarResurce>> create(@RequestBody CalendarResurce calendarResurce) {
        return ResponseEntity.ok(calendarFacade.createAll(calendarResurce));
    }

    @PostMapping("/save")
    public ResponseEntity<CalendarResurce> save(@RequestBody CalendarSaveResurce calendarResurce) {
        return ResponseEntity.ok(calendarFacade.save(calendarResurce));
    }




}
