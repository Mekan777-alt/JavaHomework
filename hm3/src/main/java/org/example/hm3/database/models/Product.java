package org.example.hm3.database.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
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
    @GeneratedValue()
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private Float price;

    @Column(name="product_count")
    private int productCount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", insertable = true, updatable = true)
    @JsonManagedReference
    private Category category;
}
