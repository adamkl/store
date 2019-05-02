package com.adamkl.store.application;

public interface Store {
    /*
     * Add item with given unique name (ID) and quantity into * cart. Name is case-sensitive.
     */
    void addItemToCart(String shopperId, String name, int quantity);

    /*
     * Add item with given unique name (ID) and price into store * inventory. Name is case-sensitive.
     */
    void addItemToStore(String name, double price);

    /*
     * Get items in cart, consolidating all duplicate items (duplicates are consolidated when added), * ordered by item name (ascending).
     */
    LineItem[] getCartItems(String shopperId);

    /*
     * Get total cost of items currently in cart.
     */
    double getCartTotal(String shopperId);
}
