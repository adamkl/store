package com.adamkl.store.framework.repositories;

import com.adamkl.store.domain.interfaces.CartRepository;
import com.adamkl.store.domain.objects.Cart;
import com.adamkl.store.domain.objects.CartItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MockCartRepository implements CartRepository {
    private ArrayList<Cart> carts = new ArrayList<>(Arrays.asList(new Cart[]{
            new Cart(
                    "1",
                    "1",
                    new CartItem[]{
                            new CartItem("Item1", 1),
                            new CartItem("Item2", 2)
                    }
            )
    }));

    @Override
    public void persist(Cart cart) {
        this.carts = this.carts.stream()
                .map(c -> c.cartId.equals(cart.cartId) ? cart : c) // Replace existing
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Cart fetchByShopper(String shopperId) {
        return this.carts.stream()
                .filter(c -> c.shopperId.equals(shopperId))
                .findFirst()
                .get();
    }
}
