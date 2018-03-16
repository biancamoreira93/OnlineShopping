package com.example.biancamoreira.onlineshopping.shoppingList;

import com.example.biancamoreira.onlineshopping.model.ShoppingItem;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.observers.TestObserver;

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

        assertEquals(shoppingItems.size(), 10);
    }
}
