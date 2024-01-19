package com.test.form.model;

import javax.persistence.*;

@Entity
@Table (name = "bills_products")
public class BillsProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idbillproduct")
    private Long idbillproduct;

    @Column(name = "idproduct")
    private Long idproduct;

    @Column(name = "idbills")
    private Long idbills;

    public Long getIdbillproduct() {
        return idbillproduct;
    }

    public void setIdbillproduct(Long idbillproduct) {
        this.idbillproduct = idbillproduct;
    }

    public Long getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(Long idproduct) {
        this.idproduct = idproduct;
    }

    public Long getIdbills() {
        return idbills;
    }

    public void setIdbills(Long idbills) {
        this.idbills = idbills;
    }
}
