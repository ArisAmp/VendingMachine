package com.vendingmachine.vendingmachine.repository;

import com.vendingmachine.vendingmachine.model.Balance;
import com.vendingmachine.vendingmachine.model.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class VendingMachineConfig {

    @Bean
    CommandLineRunner commandLineRunner(VendingMachineRepository repository, BalanceRepository balanceRepository){
        return args -> {
            Product snickers = new Product(
                    "Snickers",
                    2,
                    1.5);
            Product coke = new Product(
                    "Coca Cola",
                    10,
                    0.8);
            Product water = new Product(
                    "Water",
                    12,
                    1.2);
            Balance balance = new Balance(5);

            repository.saveAll(List.of(snickers, coke, water));
            balanceRepository.save(balance);
        };
    }
}
