package com.adamkl.store.application;

public interface LineItem {
    /*
     * Get unique item name (ID).
     */
    String getName();
    /*
     * Get item quantity.
     */
    int getQuantity();
}
