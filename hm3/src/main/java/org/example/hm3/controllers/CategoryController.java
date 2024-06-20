package org.example.hm3.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.hm3.database.models.Category;
import org.example.hm3.database.models.Product;
import org.example.hm3.models.dto.CategoryDTO;
import org.example.hm3.models.dto.ResponseProductDTO;
import org.example.hm3.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@Tag(name="CRUD по категориям")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    @Operation(summary = "Возвращает массив категорий")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping("/category")
    @Operation(summary = "Создание категории")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category newCategory = categoryService.insertCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }

    @GetMapping("/category/{category_id}")
    @Operation(summary = "Возвращает продукты по данной категории")
    public ResponseEntity<List<ResponseProductDTO>> getCategoryById(@PathVariable Long category_id) {
        List<Product> products = categoryService.getCategoryProducts(category_id);
        List<ResponseProductDTO> listResponseProductDTO = new ArrayList<>();
        for (Product product : products) {
            ResponseProductDTO responseProductDTO = ResponseProductDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .productCount(product.getProductCount()).build();
            listResponseProductDTO.add(responseProductDTO);
        }
        return ResponseEntity.status(HttpStatus.OK).body(listResponseProductDTO);
    }

    @PutMapping("/category/{category_id}")
    @Operation(summary = "Обновляет информацию о категории по id")
    public ResponseEntity<Category> updateCategoryInfo(@PathVariable Long category_id, CategoryDTO categoryDTO) {
        Category updateCategory = categoryService.updateCategory(category_id, categoryDTO);

        return ResponseEntity.status(HttpStatus.OK).body(updateCategory);
    }

    @DeleteMapping("/category/{category_id}")
    @Operation(summary = "Удаление категории по ID")
    public ResponseEntity<String> deleteCategory(@PathVariable Long category_id) {
        boolean deleteCategory = categoryService.deleteCategory(category_id);
        if (deleteCategory) {
            return ResponseEntity.ok().body("Category deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("Category not found");
        }
    }
}
