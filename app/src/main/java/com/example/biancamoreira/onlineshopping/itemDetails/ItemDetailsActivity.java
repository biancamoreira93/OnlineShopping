package com.example.biancamoreira.onlineshopping.itemDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.biancamoreira.onlineshopping.R;
import com.example.biancamoreira.onlineshopping.ShoppingNavigator;
import com.example.biancamoreira.onlineshopping.model.ShoppingItem;
import com.example.biancamoreira.onlineshopping.shoppingCart.ShoppingCartActivity;

public class ItemDetailsActivity extends AppCompatActivity {

    ShoppingItem shoppingItem;
    private ShoppingNavigator navigator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        setToolBar();

        navigator = new ShoppingNavigator();
        shoppingItem = (ShoppingItem) getIntent().getSerializableExtra("shoppingItem");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 1) {
            finish();
        }
    }

    private void setToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(listener -> onBackPressed());
    }

    public void addToCart(View view) {
        navigator.startShoppingCartActivity(this, shoppingItem);
    }
}
