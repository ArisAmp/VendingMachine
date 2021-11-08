package com.vendingmachine.vendingmachine.service.impl;

import com.vendingmachine.vendingmachine.model.Balance;
import com.vendingmachine.vendingmachine.model.Product;
import com.vendingmachine.vendingmachine.repository.VendingMachineRepository;
import com.vendingmachine.vendingmachine.service.MachineActions;
import com.vendingmachine.vendingmachine.service.MachineState;
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
    private final MachineState machineState;
    private final MachineActions machineActions;

    @Autowired
    public VendingMachineServiceImpl(VendingMachineRepository vendingMachineRepository, MachineState machineState, MachineActions machineActions) {
        this.vendingMachineRepository = vendingMachineRepository;
        this.machineState = machineState;
        this.machineActions = machineActions;
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
    public void deleteProduct(Long productID) {
        boolean exists = vendingMachineRepository.existsById(productID);
        if (!exists){
            throw new IllegalStateException("Product with Id " + productID + " does not exist.");
        }
        vendingMachineRepository.deleteById(productID);
    }

    @Transactional
    public void updateProduct(Long productID, String name, Integer quantity, Double price) {

        Product product = vendingMachineRepository.findById(productID).orElseThrow(() -> new IllegalStateException(
                "Product with Id " + productID + " does not exist."));

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

    @Override
    public Product getProduct(Long productID) {
        return vendingMachineRepository.findById(productID)
                .orElseThrow(()-> new IllegalStateException("Product with Id " + productID + " does not exist."));

    }

    @Override
    public void addBalance(double amount) {
        machineState.addBalance(amount);
    }

    @Override
    public void dispenseItem(Long id) {
        Product p = getProduct(id);
        machineState.chargeAmount(p.getPrice());
        machineActions.dispenseProduct(p);
    }

    @Override
    public Balance getBalance() {
        return machineState.getBalance();
    }

}
