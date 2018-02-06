package com.example.biancamoreira.onlineshopping.model;

import java.io.Serializable;

public class ShoppingListItem implements Serializable {
    private String itemName;
    private String itemPictureSquared;
    private String description;
    private String price;
    private boolean availability;
    private int itemsInStock;

    public ShoppingListItem(String itemName,
                            String itemPictureSquared,
                            String description,
                            String price,
                            boolean availability,
                            int itemsInStock) {
        this.itemName = itemName;
        this.itemPictureSquared = itemPictureSquared;
        this.description = description;
        this.price = price;
        this.availability = availability;
        this.itemsInStock = itemsInStock;
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
