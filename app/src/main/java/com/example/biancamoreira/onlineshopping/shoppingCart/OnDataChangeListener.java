package com.example.biancamoreira.onlineshopping.shoppingCart;

import com.example.biancamoreira.onlineshopping.domain.ShoppingItem;

import java.util.List;

public interface OnDataChangeListener {
    void dataChanged(List<ShoppingItem> shoppingItems);
}
