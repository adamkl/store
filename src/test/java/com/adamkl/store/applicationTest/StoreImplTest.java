package com.adamkl.store.applicationTest;

import com.adamkl.store.application.StoreImpl;

import com.adamkl.store.domain.interfaces.CartRepository;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import com.adamkl.store.domain.interfaces.InventoryRepository;
import com.adamkl.store.domain.objects.InventoryItem;

class StoreImplTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Nested
    @DisplayName("Test adding items to store")
    class AddItemToStoreFeature {
        InventoryRepository mockInventoryRepository;
        StoreImpl store;

        @Captor
        ArgumentCaptor<InventoryItem> captor;

        @BeforeEach
        public void setup() {
            MockitoAnnotations.initMocks(this);
            mockInventoryRepository = mock(InventoryRepository.class);
            store = new StoreImpl(mockInventoryRepository);
        }


        @Test
        void addingItemToStoreShouldUpdateInventory() {
            store.addItemToStore("Item1", 9.99);
            verify(mockInventoryRepository).persistInventoryItem(captor.capture());
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
        ArgumentCaptor<InventoryItem> captor;

        @BeforeEach
        public void setup() {
            MockitoAnnotations.initMocks(this);
            mockInventoryRepository = mock(InventoryRepository.class);
            store = new StoreImpl(mockInventoryRepository);
        }


        @Test
        void addingItemToStoreShouldUpdateInventory() {
            store.addItemToStore("Item1", 9.99);
            verify(mockInventoryRepository).persistInventoryItem(captor.capture());
            var persistedItem = captor.getValue();
            assertEquals("Item1", persistedItem.name);
            assertEquals(9.99, persistedItem.price);
        }


    }
}