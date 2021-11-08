package com.vendingmachine.vendingmachine.service;

import com.vendingmachine.vendingmachine.model.Balance;

public interface MachineState {
    Balance getBalance();
    void addBalance(double amount);
    void chargeAmount(double amount);
    Balance updateBalanceAdd(Balance b, double amount);
    Balance updateBalanceSub(Balance b, double amount);

}
