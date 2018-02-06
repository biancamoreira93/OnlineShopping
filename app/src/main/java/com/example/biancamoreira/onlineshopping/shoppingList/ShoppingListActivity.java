package com.example.biancamoreira.onlineshopping.shoppingList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;

import com.example.biancamoreira.onlineshopping.ItemDetailsActivity;
import com.example.biancamoreira.onlineshopping.R;
import com.example.biancamoreira.onlineshopping.model.ShoppingListItem;

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
    }

    private void bind() {
        compositeDisposable = new CompositeDisposable();

        compositeDisposable.add(shoppingListViewModel.getAvailableShoppingItems()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setShoppingListOptions)
        );

    }

    private void setShoppingListOptions(List<ShoppingListItem> shoppingListItems) {
        shoppingListAdapter = new ShoppingListAdapter(this, R.id.shoppingListOptions, shoppingListItems);
        shoppingListOptions.setAdapter(shoppingListAdapter);
        shoppingListOptions.setOnItemClickListener((parent, view, position, id) -> {
            getItemClicked(position);
        });
    }

    private void getItemClicked(int position) {
        ShoppingListItem shoppingListItem = (ShoppingListItem) shoppingListOptions.getItemAtPosition(position);
        callDetailsActivity(shoppingListItem);
    }

    private void callDetailsActivity(ShoppingListItem shoppingListItem) {
        Intent intent = new Intent(this, ItemDetailsActivity.class);
        intent.putExtra("shoppingListItem", shoppingListItem);
        startActivity(intent);
    }


    public void searchItem(View view) {
        String search = searchText.getText().toString();
        shoppingListAdapter.getFilter().filter(search);
    }
}
