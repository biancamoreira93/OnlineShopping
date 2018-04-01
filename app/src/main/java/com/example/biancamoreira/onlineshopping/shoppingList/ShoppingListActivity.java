package com.example.biancamoreira.onlineshopping.shoppingList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;

import com.example.biancamoreira.onlineshopping.TextWatcherOnTextChanged;
import com.example.biancamoreira.onlineshopping.itemDetails.ItemDetailsActivity;
import com.example.biancamoreira.onlineshopping.R;
import com.example.biancamoreira.onlineshopping.domain.ShoppingItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ShoppingListActivity extends AppCompatActivity {


    @BindView(R.id.shoppingListOptions)
    GridView shoppingListOptions;

    @BindView(R.id.searchText)
    EditText searchText;

    private ShoppingListAdapter shoppingListAdapter;
    private CompositeDisposable compositeDisposable;
    private ShoppingListViewModel shoppingListViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        ButterKnife.bind(this);

        shoppingListViewModel = new ShoppingListViewModel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bind();
        setListeners();
    }

    private void bind() {
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(shoppingListViewModel.getAvailableShoppingItems()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setShoppingListOptions)
        );

    }

    private void setListeners() {
        searchText.addTextChangedListener(new TextWatcherOnTextChanged() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String search = searchText.getText().toString();
                shoppingListAdapter.performFiltering(search);
            }
        });
    }

    private void setShoppingListOptions(List<ShoppingItem> shoppingItems) {
        shoppingListAdapter = new ShoppingListAdapter(this, R.id.shoppingListOptions, shoppingItems);
        shoppingListOptions.setAdapter(shoppingListAdapter);
        shoppingListOptions.setOnItemClickListener((parent, view, position, id) -> {
            getItemClicked(position);
        });
    }

    private void getItemClicked(int position) {
        ShoppingItem shoppingItem = (ShoppingItem) shoppingListOptions.getItemAtPosition(position);
        callDetailsActivity(shoppingItem);
    }

    private void callDetailsActivity(ShoppingItem shoppingItem) {
        Intent intent = new Intent(this, ItemDetailsActivity.class);
        intent.putExtra("shoppingItem", shoppingItem);
        startActivity(intent);
    }

}
