package com.adamkl.store.domain.objects;

public class InventoryItem {
    public final String name;
    public final Double price;

    public InventoryItem(String name, Double price) {
        this.name = name;
        this.price = price;
    }
}
