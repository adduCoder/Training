package com.banking.service;

import com.banking.DTO.AccountDetails;
import com.banking.DTO.CustomerDto;
import com.banking.ExceptionHandler.NoCustomerFound;
import com.banking.entities.Account;
import com.banking.entities.Customer;
import com.banking.repo.AccountRepo;
import com.banking.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private AccountRepo accountRepo;

    @GetMapping("/home")
    public void greet() {
        System.out.println("hello world");
    }

    public Customer addCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setAddress(customerDto.getAddress());
        customer.setManagerId(customerDto.getManagerId());
        if (customerDto.getManagerId() == null || customerDto.getFirstName() == null || customerDto.getAddress() == null || customerDto.getLastName() == null)
            return null;

        return customerRepo.save(customer);
    }

    public Customer deleteCustomer(Integer customerId) {
        Optional<Customer> optionalCustomer = customerRepo.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            throw new NoCustomerFound();
        }
        List<Account> accountList = accountRepo.findByCustomerId(customerId);
        for (Account account : accountList) {
            accountRepo.delete(account);
        }
        Customer customer = optionalCustomer.get();
        customerRepo.deleteById(customerId);
        return customer;
    }

    public Customer viewCustomer(Integer customerId) {
        Optional<Customer> optionalCustomer = customerRepo.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            throw new NoCustomerFound();
        }
        return optionalCustomer.get();
    }

    public Customer updateCustomer(Integer customerId, String firstName, String lastName, String address, Integer managerId) {
        Optional<Customer> optionalCustomer = customerRepo.findById(customerId);
        if (optionalCustomer.isEmpty())
            throw new NoCustomerFound();
        Customer customer = optionalCustomer.get();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setAddress(address);
        customer.setManagerId(managerId);
        return customerRepo.save(customer);
    }

    public List<Customer> viewAllCustomers() {
        List<Customer> customerList = customerRepo.findAll();
        return customerList;
    }

    public AccountDetails getAccountDetails(Integer customerId) {
        List<Account> accountList=accountRepo.findByCustomerId(customerId);
        Optional<Customer> optionalCustomer=customerRepo.findById(customerId);
        if(optionalCustomer.isEmpty())
            throw new NoCustomerFound();
        Customer customer=optionalCustomer.get();
        double salaryAmount=0;
        double currentAmount=0;
        double savingAmount=0;
        for(Account account:accountList){
            if(account.getAccountType().toString().equals("SALARY"))
                salaryAmount+=account.getBalance();
            else if(account.getAccountType().toString().equals("SAVING"))
                savingAmount+=account.getBalance();
            else if(account.getAccountType().toString().equals("CURRENT"))
                currentAmount+=account.getBalance();
        }
        AccountDetails accountDetails=new AccountDetails();
        accountDetails.setFirstName(customer.getFirstName());
        accountDetails.setLastName(customer.getLastName());
        Double total=salaryAmount+currentAmount+savingAmount;
        String details="SalaryAccount Total Amount : "+salaryAmount+
                " CurrentAccount Total Amount : "+currentAmount+
                " SavingAccount Total Amount : "+savingAmount+
                " Total : "+total;
        accountDetails.setDetails(details);
        return accountDetails;
    }
}
