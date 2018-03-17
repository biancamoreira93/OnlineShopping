package com.example.biancamoreira.onlineshopping.shoppingList;

import com.example.biancamoreira.onlineshopping.domain.ShoppingItem;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import rx.observers.TestObserver;

import static junit.framework.Assert.assertEquals;


public class ShoppingListDataModelTest {

    private ShoppingListDataModel shoppingListDataModel;

    @Before
    public void setUp() throws Exception {
        shoppingListDataModel = new ShoppingListDataModel();
    }

    @Test
    public void shouldGetShoppingListFromObservable() {
        List<ShoppingItem> shoppingItems = shoppingListDataModel.getShoppingItems().blockingFirst();

        assertEquals(9, shoppingItems.size());
    }
}
