package com.test.form.model;

import javax.persistence.*;

@Entity
@Table (name = "provider")
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idprovider")
    private Long idprovider;

    @Column(name = "name")
    private String name;

    public Long getIdprovider() {
        return idprovider;
    }

    public void setIdprovider(Long idprovider) {
        this.idprovider = idprovider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
