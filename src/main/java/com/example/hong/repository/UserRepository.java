package com.example.hong.repository;

import com.example.hong.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query(value = "select u.email from User u where u.email = :email")
    Optional<User> findByOAuthEmail(String email);

    Optional<User> findByEmailAndProvider(String email, String provider);

    Optional<User> findByName(String name);

    @Query(value = "select u from User u where u.email = :email and u.password = :password")
    Long existsById(String email, String password);

    @Query(value = "select count(o) from Order o where o.user.email = :email")
    Long countOrder(@Param("email") String email);

    @Query(value = "select u from User u where u.email = :email")
    User findUser(String email);
}
