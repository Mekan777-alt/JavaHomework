package org.example.hm3.service;

import org.example.hm3.database.models.Product;
import org.example.hm3.database.repository.ProductRepository;
import org.example.hm3.models.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product insertProduct(ProductDTO productDTO) {
        Product product = Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .productCount(productDTO.getCountProduct())
                .build();
        productRepository.save(product);

        return product;
    }

    public Product updateProduct(ProductDTO productDTO, int article_id) {
        productRepository.productUpdate(article_id,
                productDTO.getName(),
                productDTO.getPrice(),
                productDTO.getCountProduct());

        Optional<Product> product = productRepository.findById(Long.valueOf(article_id));
        return product.get();
    }

    public boolean deleteProduct(int article_id) {
        try {
            productRepository.deleteById(Long.valueOf(article_id));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
