package com.vendingmachine.vendingmachine.service;

import com.vendingmachine.vendingmachine.model.Product;
import com.vendingmachine.vendingmachine.repository.VendingMachineRepository;

import java.util.List;

public interface VendingMachineService {

    List<Product> getAllProducts();

    Product addProduct(Product p);

    void deleteProduct(Long productId);

    void updateProduct(Long productId, String name, Integer quantity, Double price);
}
