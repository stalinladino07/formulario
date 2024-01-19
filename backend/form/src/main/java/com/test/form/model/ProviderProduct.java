package com.test.form.model;

import javax.persistence.*;

@Entity
@Table(name = "provider_product")
public class ProviderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproductprovider")
    private Long idproductprovider;

    @Column(name = "idprovider")
    private Long idprovider;

    @Column(name = "idproduct")
    private Long idproduct;

    public Long getIdproductprovider() {
        return idproductprovider;
    }

    public void setIdproductprovider(Long idproductprovider) {
        this.idproductprovider = idproductprovider;
    }

    public Long getIdprovider() {
        return idprovider;
    }

    public void setIdprovider(Long idprovider) {
        this.idprovider = idprovider;
    }

    public Long getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(Long idproduct) {
        this.idproduct = idproduct;
    }
}