package com.adamkl.store.domain.objects;

public class CartItem {
    public final String name;
    private int quantity;

    public CartItem(String name, int initialQuantity) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name");
        if (initialQuantity <= 0) throw new IllegalArgumentException("initialQuantity cannot be zero or less");

        this.name = name;
        this.quantity = initialQuantity;
    }

    public void addQuantity(int amount) {
        if (this.quantity + amount < 0)
            throw new IllegalArgumentException("resulting quantity cannot be less than zero");
        this.quantity += amount;
    }

    public int getQuantity() {
        return this.quantity;
    }
}
