package com.example.biancamoreira.onlineshopping.shoppingCart;


import com.example.biancamoreira.onlineshopping.domain.ShoppingItem;

import java.util.List;

import io.reactivex.Observable;

public class ShoppingCartViewModel {
    public Observable<Double> updateShoppingCartTotalPrice(List<ShoppingItem> shoppingItems) {
        return Observable.just(calculateShoppingCartNewPrice(shoppingItems));
    }

    private Double calculateShoppingCartNewPrice(List<ShoppingItem> shoppingItems) {
        double newValue = 0;
        for (ShoppingItem shoppingItem : shoppingItems) {
            double addValue = Double.parseDouble(shoppingItem.getItemPrice());
            newValue += addValue;
        }
        return newValue;
    }
}
