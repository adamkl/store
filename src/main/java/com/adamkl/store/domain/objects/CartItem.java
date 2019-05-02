package com.adamkl.store.domain.objects;

public class CartItem {
    public final String name;
    private int quantity;

    /**
     * Creates a new cart item with a name and initial quantity.
     * Name cannot be null/blank, and the intialQuantity must be
     * greater than zero
     *
     * @param name unique identifier of the cart item
     * @param initialQuantity intial quantity of the cart item
     * @throws IllegalArgumentException thrown when name is not provided, or initial quantity is zero or less
     */
    public CartItem(String name, int initialQuantity) throws IllegalArgumentException {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name");
        if (initialQuantity <= 0) throw new IllegalArgumentException("initialQuantity cannot be zero or less");

        this.name = name;
        this.quantity = initialQuantity;
    }

    /**
     * Add quantity to the item. Negative amount may be added to
     * decrease the quantity, but the quantity cannot be reduced below
     * zero
     * @param amount Amount to change quantity by. Can be positive or negative
     * @throws IllegalArgumentException Thrown when quantity reduced below zero
     */
    public void addQuantity(int amount) throws IllegalArgumentException {
        if (this.quantity + amount < 0)
            throw new IllegalArgumentException("resulting quantity cannot be less than zero");
        this.quantity += amount;
    }

    /**
     * Gets the current item quantity
     * @return quantity
     */
    public int getQuantity() {
        return this.quantity;
    }
}
