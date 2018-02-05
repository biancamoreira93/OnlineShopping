package com.example.biancamoreira.onlineshopping.shoppingList;

import android.support.annotation.NonNull;

import com.example.biancamoreira.onlineshopping.model.ShoppingListItem;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

public class ShoppingListDataModel {

    public Observable<List<ShoppingListItem>> getShoppingItems() {
         return Observable.fromCallable(this::getShoppingListItems);
    }

    @NonNull
    private List<ShoppingListItem> getShoppingListItems() {
        return Arrays.asList(
                new ShoppingListItem("Blusa Manga Longa", "", "", "56.90", true),
                new ShoppingListItem("Blusa Manga Curta", "", "", "90.90", true),
                new ShoppingListItem("Blusa Listrada", "", "", "120.90", true),
                new ShoppingListItem("Blusa Manta", "", "", "90.90", true),
                new ShoppingListItem("Blusa de Alcinha", "", "", "85.90", true),
                new ShoppingListItem("Vestido Mid Listrado", "", "", "75.90", true),
                new ShoppingListItem("Vestido Longo Listrado", "", "", "50.90", true),
                new ShoppingListItem("Blusa Preta Abertura Nas Costas", "", "", "190.90", true),
                new ShoppingListItem("Blusa Gola Alta", "", "", "130.90", true),
                new ShoppingListItem("Short Jeans Rasgado", "", "", "139.9", false));
    }
}
