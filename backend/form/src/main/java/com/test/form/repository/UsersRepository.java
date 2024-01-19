package com.test.form.repository;

import com.test.form.model.Bills;
import com.test.form.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query(value = "SELECT * FROM users WHERE users.email = :email AND users.password = :password", nativeQuery = true)
    Users getLogin(String email, String password);
}
