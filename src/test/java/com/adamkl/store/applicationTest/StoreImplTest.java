package com.adamkl.store.applicationTest;

import com.adamkl.store.application.StoreImpl;

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
    @DisplayName("Test Adding items to cart")
    class AddItemToStoreFeature {

        @BeforeEach
        public void setup() {
            MockitoAnnotations.initMocks(this);
        }

        @Captor
        ArgumentCaptor<InventoryItem> captor;

        @Test
        void addingItemToStoreShouldUpdateInventory() {
            InventoryRepository mockInventoryRepository = mock(InventoryRepository.class);
            var store = new StoreImpl(mockInventoryRepository);
            store.addItemToStore("Item1", 9.99);
            verify(mockInventoryRepository).persistInventoryItem(captor.capture());
            var persistedItem = captor.getValue();
            assertEquals("Item1", persistedItem.name);
            assertEquals(9.99, persistedItem.price);
        }
    }
}