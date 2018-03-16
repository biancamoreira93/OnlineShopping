package com.example.biancamoreira.onlineshopping.shoppingList;

import android.support.annotation.NonNull;

import com.example.biancamoreira.onlineshopping.model.ShoppingItem;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

public class ShoppingListDataModel {

    public Observable<List<ShoppingItem>> getShoppingItems() {
         return Observable.fromCallable(this::getShoppingListItems);
    }

    @NonNull
    private List<ShoppingItem> getShoppingListItems() {
        return Arrays.asList(
                new ShoppingItem("Blusa Manga Longa", "", "", "56.90", true, 10),
                new ShoppingItem("Blusa Manga Curta", "", "", "90.90", true, 9),
                new ShoppingItem("Blusa Listrada", "", "", "120.90", true, 8),
                new ShoppingItem("Blusa de Alcinha", "", "", "85.90", true, 12),
                new ShoppingItem("Vestido Mid Listrado", "", "", "75.90", true, 5),
                new ShoppingItem("Vestido Longo Listrado", "", "", "50.90", true, 6),
                new ShoppingItem("Blusa Preta Abertura Nas Costas", "", "", "190.90", true, 18),
                new ShoppingItem("Blusa Gola Alta", "", "", "130.90", true, 25),
                new ShoppingItem("Short Jeans Rasgado", "", "", "139.9", false, 0));
    }
}
