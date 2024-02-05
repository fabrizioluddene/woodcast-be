package it.woodcast.controller;

import it.woodcast.logic.CustomerFacade;
import it.woodcast.logic.CustomerServiceFacade;
import it.woodcast.resources.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer-service")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CustomerServiceController {
    @Autowired
    private CustomerServiceFacade customerServiceFacade;

    @GetMapping("find-all")
    public ResponseEntity<List<CustomerService>> findAll() {
        return ResponseEntity.ok(customerServiceFacade.findAll());
    }




    @PostMapping("customer/{idCustomer}/save")
    public ResponseEntity<CustomerService> save(@RequestBody CustomerService customerService,@PathVariable Integer idCustomer) {
        return ResponseEntity.ok(customerServiceFacade.save(customerService,idCustomer));
    }
}
