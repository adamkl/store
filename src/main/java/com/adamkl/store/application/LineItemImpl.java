package com.adamkl.store.application;

public class LineItemImpl implements LineItem {
    private final String name;
    private final int quantity;

    public LineItemImpl(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getQuantity() {
        return this.quantity;
    }
}
