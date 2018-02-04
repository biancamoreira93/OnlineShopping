package com.example.biancamoreira.onlineshopping;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class ShoppingListViewModel {

    private ShoppingListDataModel dataModel;

    public ShoppingListViewModel() {
        dataModel = new ShoppingListDataModel();
    }

    public Observable<List<ShoppingListItem>> getAvailableShoppingItems() {
         return dataModel.getShoppingItems()
                 .flatMapIterable(listItems -> listItems)
                 .filter(ShoppingListItem::getAvailability)
                 .toList()
                 .toObservable();
    }
}
