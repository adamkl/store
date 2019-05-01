package com.adamkl.store.domainTest.objects;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import com.adamkl.store.domain.objects.InventoryItem;

public class InventoryItemTest {
    @Test
    void creatingItemWithNullNameShouldThrowError() {
        assertThrows(IllegalArgumentException.class, () -> new InventoryItem(null, 9.99));
    }

    @Test
    void creatingItemWithBlankNameShouldThrowError() {
        assertThrows(IllegalArgumentException.class, () -> new InventoryItem(" ", 9.99));
    }

    @Test
    void creatingItemWithNegativePriceShouldThrowError() {
        assertThrows(IllegalArgumentException.class, () -> new InventoryItem("Item1", -1.0));
    }
}
