package com.vendingmachine.vendingmachine.service.impl;

import com.vendingmachine.vendingmachine.model.Product;
import com.vendingmachine.vendingmachine.repository.VendingMachineRepository;
import com.vendingmachine.vendingmachine.service.VendingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendingMachineServiceImpl implements VendingMachineService {

    private final VendingMachineRepository vendingMachineRepository;

    @Autowired
    public VendingMachineServiceImpl(VendingMachineRepository vendingMachineRepository) {
        this.vendingMachineRepository = vendingMachineRepository;
    }


    @Override
    public List<Product> fetchAllProducts() {
        return vendingMachineRepository.findAll();
    }

    @Override
    public Product saveProduct(Product p) {
        return vendingMachineRepository.save(p);
    }

}
