package com.example.biancamoreira.onlineshopping.shoppingList;

import android.support.annotation.NonNull;

import com.example.biancamoreira.onlineshopping.domain.ShoppingItem;
import com.example.biancamoreira.onlineshopping.shoppingCart.ShoppingCartViewModel;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.observers.TestObserver;


public class ShoppingCartViewModelTest {

    private ShoppingCartViewModel shoppingCartViewModel;
    private TestObserver<Double> firstDoubleTestObserver;
    private TestObserver<Double> secondDoubleTestObserver;

    @Before
    public void setUp() {
        shoppingCartViewModel = new ShoppingCartViewModel();
        firstDoubleTestObserver = new TestObserver<>();
        secondDoubleTestObserver = new TestObserver<>();
    }

    @Test
    public void updatesShoppingCartTotalPrice() {
        Double expectedFirstValue = 147.8;
        Double expectedSecondValue = 90.9;
        List<ShoppingItem> shoppingItems = getShoppingItems();

        shoppingCartViewModel.updateShoppingCartTotalPrice(shoppingItems).subscribeWith(firstDoubleTestObserver);
        firstDoubleTestObserver.assertValue(expectedFirstValue);
        shoppingItems.remove(0);
        shoppingCartViewModel.updateShoppingCartTotalPrice(shoppingItems).subscribeWith(secondDoubleTestObserver);
        secondDoubleTestObserver.assertValue(expectedSecondValue);
    }

    @NonNull
    private List<ShoppingItem> getShoppingItems() {
        return new LinkedList<>(Arrays.asList(
                    new ShoppingItem("Blusa Manga Longa", "", "", "56.90", true, 10),
                    new ShoppingItem("Blusa Manga Curta", "", "", "90.90", true, 9)));
    }
}
