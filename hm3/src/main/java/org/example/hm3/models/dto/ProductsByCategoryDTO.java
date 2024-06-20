package org.example.hm3.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hm3.database.models.Product;

import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductsByCategoryDTO {
    private Long id;
    private String name;
    private HashMap<String, List<Product>> products;
}