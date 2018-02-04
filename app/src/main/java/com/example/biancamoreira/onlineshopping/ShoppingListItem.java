package com.example.biancamoreira.onlineshopping;

class ShoppingListItem {
    private String itemName;
    private String itemPictureSquared;
    private String itemPictureHorizontal;
    private String price;
    private boolean availability;

    ShoppingListItem(String itemName, String itemPictureSquared, String itemPictureHorizontal, String price, boolean availability) {
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
}
