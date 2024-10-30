package com.baeldung.jpa.simple.repository;

import com.baeldung.jpa.simple.entity.Cart;
import com.baeldung.jpa.simple.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

//    long countCartByItems_Name(String name);


    List<Item> findItemsByName(String name);
    List<Item> findItemsByCartId(long id);
}
