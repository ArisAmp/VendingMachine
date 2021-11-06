package com.vendingmachine.vendingmachine.service.impl;

import com.vendingmachine.vendingmachine.model.Product;
import com.vendingmachine.vendingmachine.repository.VendingMachineRepository;
import com.vendingmachine.vendingmachine.service.VendingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VendingMachineServiceImpl implements VendingMachineService {

    private final VendingMachineRepository vendingMachineRepository;

    @Autowired
    public VendingMachineServiceImpl(VendingMachineRepository vendingMachineRepository) {
        this.vendingMachineRepository = vendingMachineRepository;
    }


    @Override
    public List<Product> getAllProducts() {
        return vendingMachineRepository.findAll();
    }

    @Override
    public Product addProduct(Product p) {
        Optional<Product> productOptional = vendingMachineRepository.findProductByName(p.getName());
        if (productOptional.isPresent()){
            throw new IllegalStateException("Product " + p.getName() + " already in the machine.");
        }
        return vendingMachineRepository.save(p);
    }

    @Override
    public void deleteProduct(Long productId) {
        boolean exists = vendingMachineRepository.existsById(productId);
        if (!exists){
            throw new IllegalStateException("Product with Id " + productId + " does not exist.");
        }
        vendingMachineRepository.deleteById(productId);
    }

    @Transactional
    public void updateProduct(Long productId, String name, Integer quantity, Double price) {

        Product product = vendingMachineRepository.findById(productId).orElseThrow(() -> new IllegalStateException(
                "Product with Id " + productId + " does not exist."));

        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(product.getName(), name)){
            product.setName(name);
        }

        if (!(quantity > 100) &&
                !(quantity < 0) &&
                !Objects.equals(product.getQuantity(), quantity)){
            product.setQuantity(quantity);
        }

        if (!(price <= 0) &&
                !Objects.equals(product.getPrice(),price)){
            product.setPrice(price);
        }

    }

}
