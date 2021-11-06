package com.vendingmachine.vendingmachine.controller;

import com.vendingmachine.vendingmachine.model.Product;
import com.vendingmachine.vendingmachine.service.VendingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/products" )
public class VendingMachineController {
    private final VendingMachineService vendingMachineService;


    @Autowired
    public VendingMachineController(VendingMachineService vendingMachineService) {
        this.vendingMachineService = vendingMachineService;
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return vendingMachineService.getAllProducts();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product p){
        return vendingMachineService.addProduct(p);
    }

    @DeleteMapping(path = "{productId}")
    public void deleteProduct(@PathVariable("productId") Long productId){
        vendingMachineService.deleteProduct(productId);
    }

    @PutMapping(path = "{productId}")
    public void updateProduct(@PathVariable("productId") Long productId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) Integer quantity,
                              @RequestParam(required = false) Double price){
        vendingMachineService.updateProduct(productId, name, quantity, price);

    }
}
