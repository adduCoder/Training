package com.banking.service;

import com.banking.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.entities.Customer;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("/home")
    public void greet(){
        System.out.println("hello world");
    }

    public Customer addCustomer(Customer customer){
        return customerRepo.save(customer);
    }

    public Customer deleteCustomer(Integer customerId){
        Optional<Customer> optionalCustomer=customerRepo.findById(customerId);
        if(optionalCustomer.isEmpty()){
            return null;
        }
        Customer customer=optionalCustomer.get();
        customerRepo.deleteById(customerId);
        return customer;
    }

    public Customer viewCustomer(Integer customerId){
        Optional<Customer> optionalCustomer=customerRepo.findById(customerId);
        if(optionalCustomer.isEmpty()){
            return null;
        }
        return optionalCustomer.get();
    }

    public Customer updateCustomer(Integer customerId,String firstName, String lastName,String address){
        Optional<Customer> optionalCustomer=customerRepo.findById(customerId);
        if(optionalCustomer.isEmpty())throw new NullPointerException();
        Customer customer=optionalCustomer.get();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setAddress(address);
        return customerRepo.save(customer);
    }

    public List<Customer> viewAllCustomers(){
        List<Customer> customerList=customerRepo.findAll();
        return customerList;
    }

}
