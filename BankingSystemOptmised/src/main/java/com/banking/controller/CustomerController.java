package com.banking.controller;

import com.banking.DTO.AccountDetails;
import com.banking.DTO.CustomerDto;
import com.banking.entities.Customer;
import com.banking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public void show() {
        System.out.println("good evening");
    }

    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody CustomerDto customerDto) {
        Customer addedCustomer = customerService.addCustomer(customerDto);
        return new ResponseEntity<>(addedCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> viewCustomer(@PathVariable Integer id) {
        Customer customer = customerService.viewCustomer(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @RequestBody CustomerDto customerDto) {
        Customer customer = customerService.updateCustomer(id, customerDto.getFirstName(), customerDto.getLastName(), customerDto.getAddress(), customerDto.getManagerId());
        ResponseEntity<Customer> customerResponseEntity;
        customerResponseEntity = new ResponseEntity<>(customer, HttpStatus.OK);
        return customerResponseEntity;
    }

    @GetMapping("/viewAll")
    public ResponseEntity<List<Customer>> viewAllCustomer() {
        List<Customer> customerList = customerService.viewAllCustomers();
        ResponseEntity<List<Customer>> res = new ResponseEntity<>(customerList, HttpStatus.OK);
        return res;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Integer id) {
        Customer customer = customerService.deleteCustomer(id);
        ResponseEntity<Customer> customerResponseEntity;
        customerResponseEntity = new ResponseEntity<>(customer, HttpStatus.OK);
        return customerResponseEntity;
    }

    @GetMapping("/viewAccountDetails/{customerId}")
    public ResponseEntity<?> viewAccountDetails(@PathVariable Integer customerId){
       AccountDetails accountDetails=customerService.getAccountDetails(customerId);
       return new ResponseEntity<>(accountDetails,HttpStatus.OK);
    }
}
