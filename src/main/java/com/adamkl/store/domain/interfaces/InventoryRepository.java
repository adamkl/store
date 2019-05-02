package com.adamkl.store.domain.interfaces;

import com.adamkl.store.domain.objects.InventoryItem;

public interface InventoryRepository {
    void persist(InventoryItem item);
    InventoryItem[] fetchByIds(String[] itemIds);
}
