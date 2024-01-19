package com.test.form.model;

import java.util.List;

public class StockStore {

    private Double iva;

    private Double subtotal;

    private Double total;

    private List<Product> products;

    private Users user;

    private Provider providers;

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Provider getProviders() {
        return providers;
    }

    public void setProviders(Provider providers) {
        this.providers = providers;
    }
}
