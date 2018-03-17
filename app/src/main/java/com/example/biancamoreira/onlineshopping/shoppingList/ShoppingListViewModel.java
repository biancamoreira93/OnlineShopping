package com.example.biancamoreira.onlineshopping.shoppingList;

import com.example.biancamoreira.onlineshopping.domain.ShoppingItem;

import java.util.List;

import io.reactivex.Observable;

public class ShoppingListViewModel {

    private ShoppingListDataModel dataModel;

    public ShoppingListViewModel() {
        dataModel = new ShoppingListDataModel();
    }

    public Observable<List<ShoppingItem>> getAvailableShoppingItems() {
         return dataModel.getShoppingItems()
                 .flatMapIterable(listItems -> listItems)
                 .filter(ShoppingItem::getAvailability)
                 .toList()
                 .toObservable();
    }
}
