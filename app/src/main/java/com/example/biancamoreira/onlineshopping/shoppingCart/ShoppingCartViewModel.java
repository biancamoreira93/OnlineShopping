package com.example.biancamoreira.onlineshopping.shoppingCart;


import com.example.biancamoreira.onlineshopping.model.ShoppingListItem;

import java.util.List;

import io.reactivex.Observable;

public class ShoppingCartViewModel {
    private ShoppingCartDataModel shoppingCartDataModel;

    public ShoppingCartViewModel() {
        shoppingCartDataModel = new ShoppingCartDataModel();
    }

    public Observable<Double> updateShoppingCartTotalPrice(Double oldValue, List<ShoppingListItem> shoppingListItems) {
        return Observable.just(calculateShoppingCartNewPrice(oldValue, shoppingListItems));
    }

    private Double calculateShoppingCartNewPrice(Double oldValue, List<ShoppingListItem> shoppingListItems) {
        double newValue = oldValue;
        for (ShoppingListItem shoppingListItem: shoppingListItems) {
            double addValue = Double.valueOf(shoppingListItem.getItemPrice());
            newValue =+ addValue;
        }
        return newValue;
    }
}
