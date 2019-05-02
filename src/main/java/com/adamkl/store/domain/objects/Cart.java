package com.adamkl.store.domain.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Cart {
    public final String cartId;
    public final String shopperId;
    private ArrayList<CartItem> items;

    /**
     * Constucts a new empty cart
     * @param cartId Unique ID for the cart
     * @param shopperId Unique ID for the shopper using the cart
     */
    public Cart(String cartId, String shopperId) {
        this(cartId, shopperId, new CartItem[]{});
    }

    /**
     * Constructs new Cart with an initial list of items
     *
     * @param cartId Unique ID for the cart
     * @param shopperId Unique ID for the shopper using the cart
     * @param cartItems Intial array of items for the cart
     */
    public Cart(String cartId, String shopperId, CartItem[] cartItems) {
        if (cartId == null || cartId.isBlank()) throw new IllegalArgumentException("cartId");
        if (shopperId == null || shopperId.isBlank()) throw new IllegalArgumentException("shopperId");

        this.cartId = cartId;
        this.shopperId = shopperId;
        this.items = new ArrayList<>(Arrays.asList(cartItems));
    }

    /**
     * Returns all cart items as an array
     * @return Array of cart items
     */
    public CartItem[] getItems() {
        return this.items.toArray(CartItem[]::new);
    }

    /**
     * Adds a single item to the cart. If an item being added already exists in the cart
     * its quantity will be combined with the quantity of the incoming item
     *
     * @param item Item to be added
     */
    public void addItem(CartItem item) {
        this.addItems(new CartItem[]{item});
    }

    /**
     * Adds items to the cart. If an item being added already exists in the cart
     * its quantity will be combined with the quantity of the incoming item
     *
     * @param items Array of items to be added to cart
     */
    public void addItems(CartItem[] items) {
        var newItems = new ArrayList<CartItem>();

        Arrays.stream(items).forEach(itemToAdd -> {
            this.items.stream()
                    .filter(existingItem -> itemToAdd.name.equals(existingItem.name))
                    .findFirst()
                    .ifPresentOrElse(existingItem -> existingItem.addQuantity(itemToAdd.getQuantity()), () -> newItems.add(itemToAdd));
        });

        this.items.addAll(newItems);
    }

    /**
     * Updates the item in the cart with the same name. Negative amount values are allowed
     * to decrease the quantity of the item in the cart. Attempting to decrease the cart quantity
     * below zero will throw an exception. If an item's quantity is reduced to zero, it will be
     * removed from the cart
     *
     * @param name Unique identifier of item to update
     * @param amount Amount to change quantity by
     * @throws IllegalArgumentException Thrown when item is not found in cart
     */
    public void updateItemQuantity(String name, int amount) throws IllegalArgumentException {
        this.items.stream()
                .filter(item -> item.name.equals(name))
                .findFirst()
                .ifPresentOrElse(item -> item.addQuantity(amount), () -> {
                    throw new IllegalArgumentException("item not found");
                });

        this.items = this.items.stream()
                .filter(item -> item.getQuantity() > 0).collect(Collectors.toCollection(ArrayList::new));
    }

    public void removeItem(String name) throws IllegalArgumentException {
        var initialSize = this.items.size();    // We'll compare this to the final size to see if anything was removed


        this.items = this.items.stream()
                .filter(item -> item.name != name)
                .collect(Collectors.toCollection(ArrayList::new));

        if(initialSize == this.items.size()) { // This is a bit icky
           throw new IllegalArgumentException("item not found");
        }
    }
}
