package com.example.biancamoreira.onlineshopping;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class ShoppingListDataModel {

    public Observable<List<ShoppingListItem>> getShoppingItems() {
         return Observable.fromCallable(this::getShoppingListItems);
    }

    @NonNull
    private List<ShoppingListItem> getShoppingListItems() {
        return Arrays.asList(
                new ShoppingListItem("Blusa Manga Longa", "", "", "56.90", true),
                new ShoppingListItem("Blusa Manda Curta", "", "", "90.90", true),
                new ShoppingListItem("Short Jeans Rasgado", "", "", "139.9", false));
    }
}
