package com.test.form.repository;

import com.test.form.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM product WHERE product.idstore = :idStore ", nativeQuery = true)
    List<Product> getProductByStore(Long idStore);
}
