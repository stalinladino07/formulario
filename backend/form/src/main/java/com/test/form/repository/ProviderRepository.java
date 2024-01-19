package com.test.form.repository;

import com.test.form.model.Provider;
import com.test.form.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProviderRepository extends JpaRepository<Provider, Long> {

    @Query(value = "select * from users u where u.cedula = :cedulaUser and u.\"password\" = :password", nativeQuery = true)
    Users getLogin(String cedulaUser, String password);
}
