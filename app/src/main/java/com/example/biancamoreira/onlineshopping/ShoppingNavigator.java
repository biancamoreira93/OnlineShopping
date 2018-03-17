package com.example.biancamoreira.onlineshopping;

import android.app.Activity;
import android.content.Intent;

import com.example.biancamoreira.onlineshopping.domain.ShoppingItem;
import com.example.biancamoreira.onlineshopping.shoppingCart.ShoppingCartActivity;

public class ShoppingNavigator {

    public void startShoppingCartActivity(Activity activity, ShoppingItem shoppingItem) {
        Intent intent = new Intent(activity, ShoppingCartActivity.class);
        intent.putExtra("shoppingItem", shoppingItem);
        activity.startActivityForResult(intent, 1);
    }
}
