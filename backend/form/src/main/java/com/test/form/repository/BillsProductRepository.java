package com.test.form.repository;

import com.test.form.model.BillsProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import java.time.LocalDate;
import java.util.List;

public interface BillsProductRepository extends JpaRepository<BillsProducts, Long> {
    @Query(value = "SELECT * FROM product WHERE product.idstore = :idStore ", nativeQuery = true)
    List<BillsProducts> getOrdersByStore(Long idStore);

    @Query(value = "INSERT INTO product_order(idproduct, idorder) VALUES (:idProduct, :idOrder)", nativeQuery = true)
    BillsProducts insertProductOrder(Long idProduct, Long idOrder);

    @Query(value = "SELECT orders.dateorder, store.name as storeName, product.name as productName, product.price\n" +
            "FROM orders , product_order, product, store\n" +
            "WHERE orders.idorder = product_order.idorder\n" +
            "AND product.idproduct = product_order.idproduct\n" +
            "AND store.idstore = product.idstore\n" +
            "AND orders.idorder = :idOrder\n"+
            "AND dateorder BETWEEN :dateInit AND :dateEnd", nativeQuery = true)
    List<Tuple> getOrderByClient(Long idOrder, LocalDate dateInit , LocalDate dateEnd);

    @Modifying
    @Query(value = "DELETE FROM product_order WHERE idproductorder IN (SELECT idproductorder FROM product_order WHERE idorder = :idOrder )", nativeQuery = true)
    void deleteProductOrder(Long idOrder);

}
