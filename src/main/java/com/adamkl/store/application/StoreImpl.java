package com.adamkl.store.application;

import com.adamkl.store.domain.interfaces.InventoryRepository;
import com.adamkl.store.domain.objects.InventoryItem;

public class StoreImpl implements Store {
    private InventoryRepository inventoryRepository;

    public StoreImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public void addItemToCart(String name, int quantity) {

    }

    @Override
    public void addItemToStore(String name, double price) {
        var item = new InventoryItem(name, price);
        this.inventoryRepository.persistInventoryItem(item);
    }

    @Override
    public LineItem[] getCartItems() {
        return new LineItem[0];
    }

    @Override
    public double getCartTotal() {
        return 0;
    }
}
