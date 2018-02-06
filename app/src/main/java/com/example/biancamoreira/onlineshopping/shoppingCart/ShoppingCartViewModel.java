package com.example.biancamoreira.onlineshopping.shoppingCart;


import com.example.biancamoreira.onlineshopping.model.ShoppingItem;

import java.util.List;

import io.reactivex.Observable;

public class ShoppingCartViewModel {
    private ShoppingCartDataModel shoppingCartDataModel;

    public ShoppingCartViewModel() {
        shoppingCartDataModel = new ShoppingCartDataModel();
    }

    public Observable<Double> updateShoppingCartTotalPrice(Double oldValue, List<ShoppingItem> shoppingItems) {
        return Observable.just(calculateShoppingCartNewPrice(oldValue, shoppingItems));
    }

    private Double calculateShoppingCartNewPrice(Double oldValue, List<ShoppingItem> shoppingItems) {
        double newValue = oldValue;
        for (ShoppingItem shoppingItem : shoppingItems) {
            double addValue = Double.valueOf(shoppingItem.getItemPrice());
            newValue =+ addValue;
        }
        return newValue;
    }
}
