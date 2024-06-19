package org.example.hm3.database.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private Float price;

    @Column(name="product_count")
    private int productCount;
}
