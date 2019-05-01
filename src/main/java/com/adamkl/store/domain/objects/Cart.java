package com.adamkl.store.domain.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cart {
    public final String cartId;
    public final String shopperId;
    private ArrayList<CartItem> items;

    public Cart(String cartId, String shopperId) {
        this(cartId, shopperId, new CartItem[]{});
    }

    public Cart(String cartId, String shopperId, CartItem[] cartItems) {
        if (cartId == null || cartId.isBlank()) throw new IllegalArgumentException("cartId");
        if (shopperId == null || shopperId.isBlank()) throw new IllegalArgumentException("shopperId");

        this.cartId = cartId;
        this.shopperId = shopperId;
        this.items = new ArrayList<>(Arrays.asList(cartItems));
    }

    public CartItem[] getItems() {
        return this.items.toArray(CartItem[]::new);
    }

    public void addItem(CartItem item) {
        this.addItems(new CartItem[]{item});
    }

    public void addItems(CartItem[] items) {
        var newItems = new ArrayList<CartItem>();

        Arrays.stream(items).forEach(itemToAdd -> {
            this.items.stream()
                    .filter(existingItem -> itemToAdd.name == existingItem.name)
                    .findFirst()
                    .ifPresentOrElse(existingItem -> existingItem.addQuantity(itemToAdd.getQuantity()), () -> newItems.add(itemToAdd));
        });

        this.items.addAll(newItems);
    }

    public void updateItemQuantity(String name, int amount) {
        this.items.stream()
                .filter(item -> item.name == name)
                .findFirst()
                .ifPresentOrElse(item -> item.addQuantity(amount), () -> {
                    throw new IllegalArgumentException("item not found");
                });
    }
}
