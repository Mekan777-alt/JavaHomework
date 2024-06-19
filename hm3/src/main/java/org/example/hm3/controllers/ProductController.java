package org.example.hm3.controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.hm3.database.models.Product;
import org.example.hm3.models.dto.ProductDTO;
import org.example.hm3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Tag(name = "CRUD по продуктам")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    @Operation(summary = "Возвращает массив продуктов")
    public ResponseEntity<List<Product>> product() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @PostMapping("/product")
    @Operation(summary = "Создает продукт")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTO product) {
        Product newProduct = productService.insertProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @PutMapping("/product/{article_id}")
    @Operation(summary = "Обновляет продукт по id")
    public ResponseEntity<Product> updateProduct(@PathVariable int article_id, @RequestBody ProductDTO product) {
        Product updateProduct = productService.updateProduct(product, article_id);
        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
    }

    @DeleteMapping("/product/{article_id}")
    @Operation(summary = "Удаляет продукт по id")
    public ResponseEntity<String> deleteProduct(@PathVariable int article_id) {
        boolean deleteProduct = productService.deleteProduct(article_id);
        if (deleteProduct) {
            return ResponseEntity.ok("Product deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("Product not found");
        }
    }
}

