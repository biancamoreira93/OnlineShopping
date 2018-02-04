package com.example.biancamoreira.onlineshopping;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ShoppingListActivity extends Activity {


    @BindView(R.id.shoppingListOptions)
    ListView shoppingListOptions;
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
    }
}
