package com.adamkl.store.domainTest.objects;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import com.adamkl.store.domain.objects.CartItem;

public class CartItemTest {
    @Test
    void creatingItemWithNullNameShouldThrowError() {
        assertThrows(IllegalArgumentException.class, () -> new CartItem(null, 1));
    }

    @Test
    void creatingItemWithBlankNameShouldThrowError() {
        assertThrows(IllegalArgumentException.class, () -> new CartItem(" ", 1));
    }

    @Test
    void creatingItemWithZeroQuantityShouldThrowError() {
        assertThrows(IllegalArgumentException.class, () -> new CartItem("Item1", 0));
    }

    @Test
    void creatingItemWithNegativeQuantityShouldThrowError() {
        assertThrows(IllegalArgumentException.class, () -> new CartItem("Item1", -1));
    }

    @Test
    void addingPositiveQuantityShouldIncreaseQuantity() {
        var item = new CartItem("Item1", 1);
        item.addQuantity(2);
        assertEquals(3, item.getQuantity());
    }

    @Test
    void addingNegativeQuantityShouldDecreaseQuantity() {
        var item = new CartItem("Item1", 3);
        item.addQuantity(-2);
        assertEquals(1, item.getQuantity());
    }

    @Test
    void cartItemShouldThrowErrorIfQuantityDecreasedBelowZero() {
        var item = new CartItem("Item1", 1);
        assertThrows(IllegalArgumentException.class, () -> item.addQuantity(-2));
    }
}

