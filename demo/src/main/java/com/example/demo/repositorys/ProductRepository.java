package com.example.demo.repositorys;

import com.example.demo.entitys.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long CategoryId);

    List<Product> findTop10ByOrderBySoldQuantityDesc();

    @Query("SELECT p FROM Product p WHERE p.createdDate >= :date")
    List<Product> findRecentProducts(@Param("date") LocalDateTime date, Pageable pageable);
}
