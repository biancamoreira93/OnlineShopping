package com.example.biancamoreira.onlineshopping.cartUtils;

import com.example.biancamoreira.onlineshopping.domain.ShoppingItem;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<ShoppingItem> shoppingItems;

    public Cart() {
        if (shoppingItems == null) {
            shoppingItems = new ArrayList<>();
        }
    }

    public void addShoppingItem(ShoppingItem shoppingItem) {
        shoppingItems.add(shoppingItem);
    }

    public List<ShoppingItem> getShoppingItems() {
        return shoppingItems;
    }
}
