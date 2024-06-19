package org.example.hm3.database.repository;

import jakarta.transaction.Transactional;
import org.example.hm3.database.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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


}
