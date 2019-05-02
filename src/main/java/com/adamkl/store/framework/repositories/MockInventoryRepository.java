package com.adamkl.store.framework.repositories;

import com.adamkl.store.domain.interfaces.InventoryRepository;
import com.adamkl.store.domain.objects.InventoryItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MockInventoryRepository implements InventoryRepository {
    private ArrayList<InventoryItem> items = new ArrayList<>(Arrays.asList(new InventoryItem[]{
            new InventoryItem("Item1", 9.99),
        new InventoryItem("Item2", 19.95)
    }));

    @Override
    public void persist(InventoryItem item) {
       this.items = this.items.stream()
                                .map(i -> i.name.equals(item.name) ? item : i) // Replace existing
                                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public InventoryItem[] fetchByIds(String[] itemIds) {
        return this.items.stream()
                .filter(i -> Arrays.stream(itemIds).anyMatch(id -> i.name.equals(id)))
                .toArray(InventoryItem[]::new);
    }
}
