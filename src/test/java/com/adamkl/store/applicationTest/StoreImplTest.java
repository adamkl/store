package com.adamkl.store.applicationTest;

import com.adamkl.store.application.StoreImpl;

import com.adamkl.store.domain.interfaces.CartRepository;
import com.adamkl.store.domain.objects.Cart;
import com.adamkl.store.domain.objects.CartItem;
import org.mockito.*;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import com.adamkl.store.domain.interfaces.InventoryRepository;
import com.adamkl.store.domain.objects.InventoryItem;

class StoreImplTest {



    @AfterEach
    void tearDown() {
    }

    @Nested
    @DisplayName("Test adding items to store")
    class AddItemToStoreFeature {
        InventoryRepository mockInventoryRepository;
        CartRepository mockCartRepository;
        StoreImpl store;

        @Captor
        ArgumentCaptor<InventoryItem> captor;

        @BeforeEach
        public void setup() {
            MockitoAnnotations.initMocks(this);
            mockInventoryRepository = mock(InventoryRepository.class);
            mockCartRepository = mock(CartRepository.class);
            when(mockCartRepository.fetchByShopper("1")).thenReturn(
                    new Cart(
                            "1",
                            "1",
                            new CartItem[]{
                                    new CartItem("Item1", 1)
                            }
                    )
            );
            store = new StoreImpl(mockInventoryRepository, mockCartRepository);
        }



        @Test
        void addingItemToStoreShouldUpdateInventory() {
            store.addItemToStore("Item1", 9.99);
            verify(mockInventoryRepository).persist(captor.capture());
            var persistedItem = captor.getValue();
            assertEquals("Item1", persistedItem.name);
            assertEquals(9.99, persistedItem.price);
        }


    }

    @Nested
    @DisplayName("Test adding items to cart")
    class AddItemToCartFeature {
        InventoryRepository mockInventoryRepository;
        CartRepository mockCartRepository;
        StoreImpl store;

        @Captor
        ArgumentCaptor<Cart> captor;

        @BeforeEach
        public void setup() {
            MockitoAnnotations.initMocks(this);
            mockInventoryRepository = mock(InventoryRepository.class);
            mockCartRepository = mock(CartRepository.class);
            when(mockCartRepository.fetchByShopper("1")).thenReturn(
                    new Cart(
                            "1",
                            "1",
                            new CartItem[]{
                                    new CartItem("Item1", 1)
                            }
                    )
            );
            store = new StoreImpl(mockInventoryRepository, mockCartRepository);
        }


        @Test
        void addingItemToCartShouldUpdateCart() {
            store.addItemToCart("1", "Item2", 1);
            verify(mockCartRepository).persist(captor.capture());
            var cart = captor.getValue();
            var items = cart.getItems();
            assertEquals(2, items.length);
            assertEquals("Item2", items[1].name);
            assertEquals(1, items[1].getQuantity());
        }


    }

    @Nested
    @DisplayName("Test getting items from cart")
    class GetItemsFromCartFeature {
        InventoryRepository mockInventoryRepository;
        CartRepository mockCartRepository;
        StoreImpl store;

        @BeforeEach
        public void setup() {
            mockInventoryRepository = mock(InventoryRepository.class);
            mockCartRepository = mock(CartRepository.class);
            when(mockCartRepository.fetchByShopper("1")).thenReturn(
                    new Cart(
                            "1",
                            "1",
                            new CartItem[]{
                                    new CartItem("Item2", 1),
                                    new CartItem("Item1", 2)
                            }
                    )
            );
            store = new StoreImpl(mockInventoryRepository, mockCartRepository);
        }

        @Test
        void gettingCartItemsShouldProduceSortedLineItemsBasedOnCartContents() {
            var lineItems = store.getCartItems("1");
            assertEquals(2, lineItems.length);
            assertEquals("Item1", lineItems[0].getName());
            assertEquals(2, lineItems[0].getQuantity());
            assertEquals("Item2", lineItems[1].getName());
            assertEquals(1, lineItems[1].getQuantity());
        }


    }

    @Nested
    @DisplayName("Test getting total cost for items in cart")
    class GetTotalCostOfCartFeature {
        InventoryRepository mockInventoryRepository;
        CartRepository mockCartRepository;
        StoreImpl store;

        @BeforeEach
        public void setup() {
            mockInventoryRepository = mock(InventoryRepository.class);
            when(mockInventoryRepository.fetchByIds(new String[]{"Item1", "Item2"})).thenReturn(
                new InventoryItem[]{
                        new InventoryItem("Item1", 9.99),
                        new InventoryItem("Item2", 19.95)
                }
            );
            mockCartRepository = mock(CartRepository.class);
            when(mockCartRepository.fetchByShopper("1")).thenReturn(
                    new Cart(
                            "1",
                            "1",
                            new CartItem[]{
                                    new CartItem("Item1", 1),
                                    new CartItem("Item2", 2)
                            }
                    )
            );
            store = new StoreImpl(mockInventoryRepository, mockCartRepository);
        }

        @Test
        void gettingTotalCostOfCartShouldProduceSumBasedOnContentsAndInventory() {
            var total = store.getCartTotal("1");
            assertEquals(49.89, total);
        }


    }
}