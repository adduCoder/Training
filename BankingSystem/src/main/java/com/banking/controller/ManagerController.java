package com.banking.controller;

import com.banking.DTO.CustomerDto;
import com.banking.DTO.ManagerDto;
import com.banking.entities.Customer;
import com.banking.entities.Manager;
import com.banking.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @PostMapping
    public ResponseEntity<Manager> addManager(@RequestBody Manager manager){
        Manager addedManager= managerService.addManager(manager);
        return  new ResponseEntity<>(addedManager, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Manager> deleteManager(@PathVariable Integer id){
        Manager deletedManager=managerService.deleteManager(id);
        return new ResponseEntity<>(deletedManager,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manager> viewManager(@PathVariable Integer id){
        Manager manager=managerService.viewManager(id);
        return new ResponseEntity<>(manager,HttpStatus.OK);
    }

    @GetMapping("/viewAll")
    public ResponseEntity<List<Manager>> viewAllManager(){
        List<Manager> managerList=managerService.viewAllManager();
        return new ResponseEntity<List<Manager>>(managerList,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manager> updateManager(@PathVariable Integer id, @RequestBody ManagerDto managerDto){
        Manager manager=managerService.updateManager(id,managerDto.getFirstName(),managerDto.getLastName());
        ResponseEntity<Manager> managerResponseEntity = new ResponseEntity<>(manager, HttpStatus.OK);
        return managerResponseEntity;
    }

}
