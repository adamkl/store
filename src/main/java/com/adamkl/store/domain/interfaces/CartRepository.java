package com.adamkl.store.domain.interfaces;

import com.adamkl.store.domain.objects.Cart;

public interface CartRepository {
    void persist(Cart cart);
}
