package com.vendingmachine.vendingmachine.service;

import com.vendingmachine.vendingmachine.model.Product;
import com.vendingmachine.vendingmachine.repository.VendingMachineRepository;

import java.util.List;

public interface VendingMachineService {

    List<Product> fetchAllProducts();

    Product saveProduct(Product p);

}
