package com.example.biancamoreira.onlineshopping.model;

import java.io.Serializable;

public class ShoppingListItem implements Serializable {
    private String itemName;
    private String itemPictureSquared;
    private String itemPictureHorizontal;
    private String price;
    private boolean availability;

    public ShoppingListItem(String itemName, String itemPictureSquared, String itemPictureHorizontal, String price, boolean availability) {
        this.itemName = itemName;
        this.itemPictureSquared = itemPictureSquared;
        this.itemPictureHorizontal = itemPictureHorizontal;
        this.price = price;
        this.availability = availability;
    }

    public boolean getAvailability() {
        return availability;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemPrice() {
        return price;
    }
}
