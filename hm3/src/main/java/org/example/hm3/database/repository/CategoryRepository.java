package org.example.hm3.database.repository;

import jakarta.transaction.Transactional;
import org.example.hm3.database.models.Category;
import org.example.hm3.database.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = """
            SELECT MAX(c.id)
            FROM Category c""")
    Long findLastId();

    @Query(value = """
            SELECT * FROM category
            WHERE name = :name""", nativeQuery = true)
    Category countByName(@Param("name") String name);

    @Modifying
    @Query(value = """
            UPDATE category
            SET name=:name
            WHERE id = :id
            """, nativeQuery = true)
    void categoryUpdate(@Param("id") Long id,
                       @Param("name") String name);

}