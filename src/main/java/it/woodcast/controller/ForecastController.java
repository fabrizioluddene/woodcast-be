package it.woodcast.controller;

import it.woodcast.logic.CalendarFacade;
import it.woodcast.logic.ForecastFacade;
import it.woodcast.resources.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@RequestMapping("/forecast")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ForecastController {
    @Autowired
    private ForecastFacade customerFacade;
    @Autowired
    private CalendarFacade calendarFacade;


    @GetMapping("/{customerId}")
    public ResponseEntity<List<CalendarPivotResource>> findAllBatchRegistry(@RequestHeader String authorization,@PathVariable String customerId,@RequestParam(value = "batchRegistryId",required = false) String batchRegistryId) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        return ResponseEntity.ok(customerFacade.getAllCustomerBatchRegistry(customerId,batchRegistryId));
    }
    @GetMapping("/costi-ricavi/{customerId}")
    public ResponseEntity<RevenuesCostsResource> calculate(@RequestHeader String authorization,@PathVariable String customerId, @RequestParam(value ="batchRegistryId",required = false) String batchRegistryId) {

        return ResponseEntity.ok(customerFacade.calculate(customerId,batchRegistryId));
    }
    @PostMapping("/create")
    public ResponseEntity<List<CalendarResurce>> create(@RequestHeader String authorization,@RequestBody CalendarResurce calendarResurce) {
        return ResponseEntity.ok(calendarFacade.createAll(calendarResurce));
    }

    @PostMapping("/save")
    public ResponseEntity<CalendarResurce> save(@RequestHeader String authorization,@RequestBody CalendarSaveResurce calendarResurce) {
        return ResponseEntity.ok(calendarFacade.save(calendarResurce));
    }




}
