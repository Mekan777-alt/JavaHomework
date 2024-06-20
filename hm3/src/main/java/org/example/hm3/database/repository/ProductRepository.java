package org.example.hm3.database.repository;

import jakarta.transaction.Transactional;
import org.example.hm3.database.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Query(value = """
            UPDATE product
            SET name=:name, price=:price, product_count=:productCount
            WHERE id = :id
            """, nativeQuery = true)
    void productUpdate(@Param("id") int id,
                       @Param("name") String name,
                       @Param("price") Float price,
                       @Param("productCount") int productCount);

    @Query(value = """
            SELECT * FROM product
            WHERE category_id = :cat_id""", nativeQuery = true)
    List<Product> getProductsFromCategory(@Param("cat_id") Long cat_id);
}
