package com.adamkl.store.domainTest.objects;

import com.adamkl.store.domain.objects.CartItem;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import com.adamkl.store.domain.objects.Cart;

public class CartTest {
    @Test
    void creatingCartWithNullCartIdShouldThrowError() {
        assertThrows(IllegalArgumentException.class, () -> new Cart(null, "1"));
    }

    @Test
    void creatingCartWithBlankCartIdShouldThrowError() {
        assertThrows(IllegalArgumentException.class, () -> new Cart(" ", "1"));
    }

    @Test
    void creatingCartWithNullShopperIdShouldThrowError() {
        assertThrows(IllegalArgumentException.class, () -> new Cart("1", null));
    }

    @Test
    void creatingCartWithBlankShopperIdShouldThrowError() {
        assertThrows(IllegalArgumentException.class, () -> new Cart("1", " "));
    }

    @Test
    void creatingCartShouldSetCartIdAndShopperId() {
        var cart = new Cart("1", "1");
        assertEquals("1", cart.cartId);
        assertEquals("1", cart.shopperId);
    }

    @Test
    void creatingCartWithoutInitialItemsShouldCreateEmptyListOfItems() {
        var cart = new Cart("1", "1");
        assertEquals(0, cart.getItems().length);
    }

    @Test
    void creatingCartWithInitialItemsShouldCreateListOfItems() {
        var cart = new Cart("1", "1", new CartItem[]{new CartItem("Item1", 1)});
        assertEquals(1, cart.getItems().length);
        var item = cart.getItems()[0];
        assertEquals("Item1", item.name);
        assertEquals(1, item.getQuantity());
    }

    @Nested
    @DisplayName("Add items to cart")
    class AddItemsToCartFeature {
        Cart cart;

        @BeforeEach
        public void setup() {
            cart = new Cart("1", "1", new CartItem[]{
                    new CartItem("Item1", 1),
                    new CartItem("Item2", 1)
            });
        }

        @Test
        void addingNewItemToCartShouldAddToItemList() {
            cart.addItem(new CartItem("Item3", 1));
            var items = cart.getItems();
            assertEquals(3, items.length);
            assertEquals("Item3", items[2].name);
            assertEquals(1, items[2].getQuantity());
        }

        @Test
        void addingExistingItemToCartShouldUpdateQuantityInList() {
            cart.addItem(new CartItem("Item2", 2));
            var items = cart.getItems();
            assertEquals(2, items.length);
            assertEquals("Item2", items[1].name);
            assertEquals(3, items[1].getQuantity());
        }
    }

    @Nested
    @DisplayName("Update quantity of item")
    class UpdateItemQuantityFeature {
        Cart cart;

        @BeforeEach
        public void setup() {
            cart = new Cart("1", "1", new CartItem[]{
                    new CartItem("Item1", 1),
                    new CartItem("Item2", 1)
            });
        }

        @Test
        void shouldUpdateQuantityOfItem() {
            cart.updateItemQuantity("Item1", 2);
            var items = cart.getItems();
            assertEquals("Item1", items[0].name);
            assertEquals(3, items[0].getQuantity());
        }

    }
}


