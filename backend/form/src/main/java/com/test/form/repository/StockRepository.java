package com.test.form.repository;

import com.test.form.model.Bills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import java.util.List;

public interface StockRepository extends JpaRepository<Bills, Long> {

    @Query(value = "SELECT * FROM stock WHERE idProduct=:idProduct ", nativeQuery = true)
    Bills getProductById(Long idProduct);

    @Query(value = "select users.name as username, products.idproduct,products.name,products.price,bills.cedula,bills.subtotal,bills.iva,bills.total from users, products , bills, bills_products where users.cedula = bills.cedula and bills.idbills = bills_products.idbills and products.idproduct = bills_products.idproduct and bills.idbills = :idBill", nativeQuery = true)
    List<Tuple> getBillProduct(Long idBill);



}
