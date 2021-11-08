package com.vendingmachine.vendingmachine.controller;

import com.vendingmachine.vendingmachine.model.Balance;
import com.vendingmachine.vendingmachine.model.Product;
import com.vendingmachine.vendingmachine.service.VendingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api" )
public class VendingMachineController {
    private final VendingMachineService vendingMachineService;


    @Autowired
    public VendingMachineController(VendingMachineService vendingMachineService) {
        this.vendingMachineService = vendingMachineService;
    }

    @GetMapping(path ="/products")
    public List<Product> getAllProducts(){
        return vendingMachineService.getAllProducts();
    }

    @GetMapping(path ="/products/{productID}")
    public Product getProduct(@PathVariable("productID") Long productID){
       return vendingMachineService.getProduct(productID);
    }

    @PostMapping(path = "/products")
    public Product addProduct(@RequestBody Product p){
        return vendingMachineService.addProduct(p);
    }

    @DeleteMapping(path = "/products/{productID}")
    public void deleteProduct(@PathVariable("productID") Long productID){
        vendingMachineService.deleteProduct(productID);
    }

    @PutMapping(path = "/products/{productID}")
    public void updateProduct(@PathVariable("productID") Long productID,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) Integer quantity,
                              @RequestParam(required = false) Double price){
        vendingMachineService.updateProduct(productID, name, quantity, price);

    }

    @PostMapping(path = "/balance")
    public void addBalance(@RequestBody double amount){
        vendingMachineService.addBalance(amount);
    }

    @GetMapping(path = "/balance")
    public Balance getBalance(){
        return vendingMachineService.getBalance();
    }

    @GetMapping(path = "/balance/{id}")
    public void dispenseItem(@PathVariable("id") Long id){
        vendingMachineService.dispenseItem(id);
    }


}
