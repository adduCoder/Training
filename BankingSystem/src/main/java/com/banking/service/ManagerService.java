package com.banking.service;

import com.banking.entities.Customer;
import com.banking.entities.Manager;
import com.banking.repo.CustomerRepo;
import com.banking.repo.ManagerRepo;
import jakarta.persistence.criteria.CriteriaBuilder;
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

    public Manager addManager(Manager manager){
        return managerRepo.save(manager);
    }

    public Manager deleteManager(Integer managerId){
        Optional<Manager> optionalManager=managerRepo.findById(managerId);
        if(optionalManager.isEmpty()){
            return null;
        }
        Manager deletedManager=optionalManager.get();
        managerRepo.deleteById(managerId);
        return deletedManager;
    }

    public Manager viewManager(Integer managerId){
        Optional<Manager> optionalManager=managerRepo.findById(managerId);
        if(optionalManager.isEmpty()){
            return null;
        }
        return optionalManager.get();
    }

    public Manager updateManager(Integer managerId,String firstName, String lastName){
        Optional<Manager> optionalManager=managerRepo.findById(managerId);
        if(optionalManager.isEmpty())return null;
        Manager manager=optionalManager.get();
        manager.setFirstName(firstName);
        manager.setLastName(lastName);
        return managerRepo.save(manager);
    }

    public List<Manager> viewAllManager(){
        List<Manager> managerList=managerRepo.findAll();
        return managerList;
    }


}
