package com.comprathor.repository;

import com.comprathor.repository.entity.Category;
import com.comprathor.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.id_category = :category")
    List<Product> findByCategory(@Param("category") Optional<Category> category);
}