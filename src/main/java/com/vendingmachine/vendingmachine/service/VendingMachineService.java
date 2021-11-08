package com.vendingmachine.vendingmachine.service;

import com.vendingmachine.vendingmachine.model.Balance;
import com.vendingmachine.vendingmachine.model.Product;

import java.util.List;

public interface VendingMachineService {

    List<Product> getAllProducts();

    Product addProduct(Product p);

    void deleteProduct(Long productID);

    void updateProduct(Long productID, String name, Integer quantity, Double price);

    Product getProduct(Long productID);

    void addBalance(double amount);

    void dispenseItem(Long id);

    Balance getBalance();
}
