package com.test.form.model;

import javax.persistence.*;

@Entity
@Table(name = "bills")
public class Bills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idbills")
    private Long idbills;

    @Column(name = "cedula")
    private String cedula;

    @Column(name = "subtotal")
    private Double subtotal;

    @Column(name = "iva")
    private Double iva;

    @Column(name = "total")
    private Double total;

    public Long getIdbills() {
        return idbills;
    }

    public void setIdbills(Long idbills) {
        this.idbills = idbills;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
