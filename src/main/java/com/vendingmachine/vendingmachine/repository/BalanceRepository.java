package com.vendingmachine.vendingmachine.repository;

import com.vendingmachine.vendingmachine.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
}
