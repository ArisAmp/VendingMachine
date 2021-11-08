package com.vendingmachine.vendingmachine.service.impl;

import com.vendingmachine.vendingmachine.model.Balance;
import com.vendingmachine.vendingmachine.repository.BalanceRepository;
import com.vendingmachine.vendingmachine.service.MachineState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class MachineStateImpl implements MachineState {

    private final BalanceRepository balanceRepository;


    @Autowired
    public MachineStateImpl(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @Override
    public Balance getBalance() {
        return balanceRepository.findAll().stream().findFirst().orElseThrow(
                ()-> new IllegalStateException("No balance entries.")
        );
    }

    @Override
    public void addBalance(double amount) {
        if (amount < 0){
            throw new IllegalStateException("Negative amount not allowed.");
        }
        balanceRepository.save(updateBalanceAdd(getBalance(), amount));
    }

    @Override
    @Transactional
    public void chargeAmount(double amount) {
        if(getBalance().getAmount() - amount < 0){
            throw new IllegalStateException("Insufficient balance.");
        }
        balanceRepository.save(updateBalanceSub(getBalance(), amount));

    }

    @Override
    public Balance updateBalanceAdd(Balance b, double amount) {
        b.setAmount(b.getAmount() + amount);
        return b;
    }

    @Override
    public Balance updateBalanceSub(Balance b, double amount) {
        b.setAmount(b.getAmount() - amount);
        return b;
    }
}
