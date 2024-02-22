package it.woodcast.controller;

import it.woodcast.logic.CustomerFacade;
import it.woodcast.resources.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CustomerController {
    @Autowired
    private CustomerFacade customerFacade;

    @GetMapping("find-all")
    public ResponseEntity<List<Customer>> findAll() {
        return ResponseEntity.ok(customerFacade.findAllCustomer());
    }
    @GetMapping("batch-registry/{customerId}")
    public ResponseEntity<List<BatchRegistry>> findAllBatchRegistry(@PathVariable String customerId) {
        return ResponseEntity.ok(customerFacade.getAllCustomerBatchRegistry(customerId));
    }

    @GetMapping("{customerId}/service")
    public ResponseEntity<List<BatchRegistry>> findAllCustomerService(@PathVariable Integer customerId) {
        return  ResponseEntity.ok(customerFacade.getAllCustomerService(customerId));
    }

    @PostMapping("save")
    public ResponseEntity<Customer> save(@RequestBody Customer resource) {
        return ResponseEntity.ok(customerFacade.save(resource));
    }
}
