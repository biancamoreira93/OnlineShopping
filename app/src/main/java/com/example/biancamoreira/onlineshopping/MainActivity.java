package com.example.biancamoreira.onlineshopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.biancamoreira.onlineshopping.shoppingList.ShoppingListActivity;

public class MainActivity extends AppCompatActivity {

    private Button shoppingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bindViews();

    }

    private void bindViews() {
        shoppingButton = findViewById(R.id.startShoppingButton);
    }

    public void startShopping(View view) {
        startActivity(new Intent(this, ShoppingListActivity.class));
    }
}
