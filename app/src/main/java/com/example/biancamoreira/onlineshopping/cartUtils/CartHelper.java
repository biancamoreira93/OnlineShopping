package com.example.biancamoreira.onlineshopping.cartUtils;

public class CartHelper {
    private static Cart cart;

    public static Cart getCart() {
        if (cart == null) {
            cart = new Cart();
        }
        return cart;
    }
}
