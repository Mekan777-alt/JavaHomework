package org.example.hm3.service;

import org.example.hm3.configuration.CommonConfig;
import org.example.hm3.database.models.Category;
import org.example.hm3.database.models.Product;
import org.example.hm3.database.repository.CategoryRepository;
import org.example.hm3.database.repository.ProductRepository;
import org.example.hm3.models.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public Long categoryID() {
        Long lastID =  categoryRepository.findLastId();
        if (lastID == null) {
            return 0L;
        } else {
            return lastID;
        }
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category insertCategory(CategoryDTO categoryDTO) {
        Category category = Category.builder()
                .id(CommonConfig.count + 1)
                .name(categoryDTO.getName()).build();
        CommonConfig.count += 1;
        categoryRepository.save(category);

        return category;
    }

    public List<Product> getCategoryProducts(Long category_id) {
        return productRepository.getProductsFromCategory(category_id);
    }

    public Category updateCategory(Long category_id, CategoryDTO categoryDTO) {
        categoryRepository.categoryUpdate(category_id,
                                        categoryDTO.getName());
        Optional<Category> category = categoryRepository.findById(Long.valueOf(category_id));
        return category.get();
    }

    public boolean deleteCategory(Long category_id) {
        try {
            categoryRepository.deleteById(category_id);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
