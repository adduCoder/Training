package com.banking.service;

import com.banking.DTO.ManagerDto;
import com.banking.ExceptionHandler.InvalidOperationException;
import com.banking.ExceptionHandler.NoCustomerFound;
import com.banking.ExceptionHandler.NoManagerFound;
import com.banking.entities.Manager;
import com.banking.repo.CustomerRepo;
import com.banking.repo.ManagerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {


    @Autowired
    private ManagerRepo managerRepo;

    @Autowired
    private CustomerRepo customerRepo;

    public Manager addManager(ManagerDto managerDto) {
        if (managerDto.getFirstName() == null || managerDto.getLastName() == null)
           return null;
        Manager manager = new Manager();
        manager.setFirstName(managerDto.getFirstName());
        manager.setLastName(managerDto.getLastName());
        return managerRepo.save(manager);
    }

    public Manager deleteManager(Integer managerId) {
        Optional<Manager> optionalManager = managerRepo.findById(managerId);
        if (optionalManager.isEmpty()) {
            throw new NoManagerFound();
        }
        Manager deletedManager = optionalManager.get();
        managerRepo.deleteById(managerId);
        return deletedManager;
    }

    public Manager viewManager(Integer managerId) {
        Optional<Manager> optionalManager = managerRepo.findById(managerId);
        if (optionalManager.isEmpty()) {
            throw new NoManagerFound();
        }
        return optionalManager.get();
    }

    public Manager updateManager(Integer managerId, String firstName, String lastName) {
        Optional<Manager> optionalManager = managerRepo.findById(managerId);
        if (optionalManager.isEmpty())
            throw new NoManagerFound();
        Manager manager = optionalManager.get();
        manager.setFirstName(firstName);
        manager.setLastName(lastName);
        return managerRepo.save(manager);
    }

    public List<Manager> viewAllManager() {
        List<Manager> managerList = managerRepo.findAll();
        return managerList;
    }

}
