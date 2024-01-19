package com.test.form.repository;

import com.test.form.model.Bills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillsRepository extends JpaRepository<Bills, Long> {
    /*@Query(value = "SELECT * FROM product WHERE product.idstore = :idStore ", nativeQuery = true)
    List<OrderClient> getOrdersByStore(Long idStore);*/
}
