package com.vendingmachine.vendingmachine.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class Product {

//    @SequenceGenerator(
//            name = "vending_sequence",
//            sequenceName = "vending_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "vending_sequence"
//    )
    @Id
    @GeneratedValue
    private Long id;

    @Column (unique = true)
    private String name;

    private Integer quantity;
    private double price;

    public Product() {
    }

    public Product(String name, Integer quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 &&
                Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(quantity, product.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

}
