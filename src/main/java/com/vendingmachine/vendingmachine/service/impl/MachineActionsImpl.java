package com.vendingmachine.vendingmachine.service.impl;

import com.vendingmachine.vendingmachine.model.Product;
import com.vendingmachine.vendingmachine.repository.VendingMachineRepository;
import com.vendingmachine.vendingmachine.service.MachineActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class MachineActionsImpl implements MachineActions {
    private final VendingMachineRepository vendingMachineRepository;

    @Autowired
    public MachineActionsImpl(VendingMachineRepository vendingMachineRepository) {
        this.vendingMachineRepository = vendingMachineRepository;
    }

    @Override
    @Transactional
    public void dispenseProduct(Product p) {
        if (p.getQuantity() <= 0){
            throw new IllegalStateException(p.getName() + " not in stock.");
        } else {
            p.setQuantity(p.getQuantity() - 1);
            vendingMachineRepository.save(p);
            System.out.println("Dispensed " + p.getName() + ".");
        }
    }
}
