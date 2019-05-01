package com.adamkl.store.domain.objects;

public class InventoryItem {
    public final String name;
    public final Double price;

    public InventoryItem(String name, Double price) {
        if(name == null || name.isBlank()) throw new IllegalArgumentException("name");
        if(price < 0) throw new IllegalArgumentException("price");

        this.name = name;
        this.price = price;
    }
}
