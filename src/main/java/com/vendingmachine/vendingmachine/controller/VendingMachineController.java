package com.vendingmachine.vendingmachine.controller;

import com.vendingmachine.vendingmachine.model.Product;
import com.vendingmachine.vendingmachine.service.VendingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VendingMachineController {
    private final VendingMachineService vendingMachineService;


    @Autowired
    public VendingMachineController(VendingMachineService vendingMachineService) {
        this.vendingMachineService = vendingMachineService;
    }

    @GetMapping (path = "/products")
    public List<Product> getAllProducts(){
        return vendingMachineService.fetchAllProducts();
    }

    @PostMapping(path = "/products")
    public Product addProduct( Product p){
        return vendingMachineService.saveProduct(p);
    }
}
