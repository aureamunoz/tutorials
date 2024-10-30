package com.baeldung.jpa.simple;

import com.baeldung.jpa.simple.entity.Cart;
import com.baeldung.jpa.simple.entity.Item;
import com.baeldung.jpa.simple.repository.CartRepository;
import com.baeldung.jpa.simple.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = JpaApplication.class)
class CartRepositoryIntegrationTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void givenDbContainsBooks_whenFindBooksByAuthor_thenReturnBooksByAuthor() {
//        new Item
        Cart cart1 = new Cart();
        Item item1 = new Item("item1", cart1);
        Item item2 = new Item("item2", cart1);
        Item item3 = new Item("item3", cart1);
        Item item4 = new Item("item4", cart1);

        Cart cart2 = new Cart();
        Item item21 = new Item("item12", cart2);
        Item item22 = new Item("item22", cart2);
        Item item23 = new Item("item23", cart2);
        Item item24 = new Item("item24", cart2);


        cartRepository.saveAll(Arrays.asList(cart1,cart2));
        itemRepository.saveAll(Arrays.asList(item1,item2,item3,item4,item21,item22,item23,item24));

        List<Cart> all = cartRepository.findAll();
        Assertions.assertEquals(2,all.size());

        List<Item> item11 = itemRepository.findItemsByName("item1");
        Assertions.assertEquals(1,item11.size());

        List<Item> itemsByCartId = itemRepository.findItemsByCartId(1);
        Assertions.assertEquals(4,itemsByCartId.size());


        long allItem1s = cartRepository.countCartByItems_Name("item1");

        Assertions.assertEquals(1,allItem1s);

    }
}