package com.example.biancamoreira.onlineshopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.biancamoreira.onlineshopping.model.ShoppingItem;
import com.example.biancamoreira.onlineshopping.shoppingCart.ShoppingCartActivity;

public class ItemDetailsActivity extends AppCompatActivity {

    ShoppingItem shoppingItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        setToolBar();

        shoppingItem = (ShoppingItem) getIntent().getSerializableExtra("shoppingItem");
    }

    private void setToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(listener -> onBackPressed());
    }

    public void addToCart(View view) {
        /*TODO Navigator */
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        intent.putExtra("shoppingItem", shoppingItem);
        startActivity(intent);
    }
}
