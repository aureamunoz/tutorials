package com.baeldung.jpa.simple.repository;

import com.baeldung.jpa.simple.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    long countCartByItems_Name(String name);
}
