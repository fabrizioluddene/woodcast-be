package it.woodcast.controller;

import it.woodcast.logic.CalendarFacade;
import it.woodcast.logic.ForecastFacade;
import it.woodcast.resources.*;
import it.woodcast.resources.dashboard.graph.CalendarGraphDashbordResource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
    public ResponseEntity<List<CalendarPivotResource>> findAllBatchRegistry(@PathVariable String customerId,@RequestParam(value = "batchRegistryId",required = false) String batchRegistryId) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        return ResponseEntity.ok(customerFacade.getAllCustomerBatchRegistry(customerId,batchRegistryId));
    }
    @GetMapping("/costi-ricavi/{customerId}")
    public ResponseEntity<RevenuesCostsResource> calculate(@PathVariable String customerId, String batchRegistryId) {

        return ResponseEntity.ok(customerFacade.calculate(customerId,batchRegistryId));
    }
    @GetMapping("dashboard/{customerId}")
    public ResponseEntity<List<CalendarGraphDashbordResource>> findAllBatchRegistryDasboard( String authorization, @PathVariable String customerId, @RequestParam(value = "batchRegistryId",required = false) String batchRegistryId) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        return ResponseEntity.ok(customerFacade.getAllCustomerBatchRegistryDashboard(customerId,batchRegistryId));
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
