package com.vendingmachine.vendingmachine.repository;

import com.vendingmachine.vendingmachine.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendingMachineRepository extends JpaRepository<Product, Long> {

}
