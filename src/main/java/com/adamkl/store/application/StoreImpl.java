package com.adamkl.store.application;

import com.adamkl.store.domain.interfaces.CartRepository;
import com.adamkl.store.domain.interfaces.InventoryRepository;
import com.adamkl.store.domain.objects.CartItem;
import com.adamkl.store.domain.objects.InventoryItem;

import java.util.Arrays;
import java.util.Comparator;

public class StoreImpl implements Store {
    private final InventoryRepository inventoryRepository;
    private final CartRepository cartRepository;

    public StoreImpl(InventoryRepository inventoryRepository, CartRepository cartRepository) {
        this.inventoryRepository = inventoryRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public void addItemToCart(String shopperId, String name, int quantity) {
        var cartItem = new CartItem(name, quantity);
        var cart = this.cartRepository.fetchByShopper(shopperId);
        cart.addItem(cartItem);
        this.cartRepository.persist(cart);
    }

    @Override
    public void addItemToStore(String name, double price) {
        var item = new InventoryItem(name, price);
        this.inventoryRepository.persist(item);
    }

    @Override
    public LineItem[] getCartItems(String shopperId) {
        var cart = this.cartRepository.fetchByShopper(shopperId);
        return Arrays.stream(cart.getItems())
                .map(item -> new LineItemImpl(item.name, item.getQuantity()))
                .sorted(Comparator.comparing(LineItemImpl::getName))
                .toArray(LineItem[]::new);
    }

    @Override
    public double getCartTotal(String shopperId) {
        var cart = this.cartRepository.fetchByShopper(shopperId);
        var cartItems = cart.getItems();
        var itemIds = Arrays.stream(cartItems)
                .map(i -> i.name)
                .toArray(String[]::new);
        var inventoryItems = this.inventoryRepository.fetchByIds(itemIds);

        // assuming inventory repository returns items in same order,
        // we'll do a simple for-loop across both arrays to calculate total
        double total = 0;
        for(int i = 0; i < cartItems.length; i++) {
            total += cartItems[i].getQuantity() * inventoryItems[i].price;
        }
        return total;
    }
}
