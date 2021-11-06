package com.vendingmachine.vendingmachine.repository;

import com.vendingmachine.vendingmachine.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendingMachineRepository extends JpaRepository<Product, Long> {

    @Query
    Optional<Product> findProductByName(String name);
}
