package com.example.biancamoreira.onlineshopping.shoppingCart;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.biancamoreira.onlineshopping.R;
import com.example.biancamoreira.onlineshopping.model.ShoppingListItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ShoppingCartActivity extends Activity {

    @BindView(R.id.shoppingCartList)
    ListView shoppingCartList;

    @BindView(R.id.totalPriceText)
    TextView totalPrice;

    private ShoppingCartListAdapter shoppingCartListAdapter;
    private CompositeDisposable compositeDisposable;
    private ShoppingCartViewModel shoppingCartViewModel;
    private List<ShoppingListItem> shoppingListItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        ButterKnife.bind(this);

        shoppingCartViewModel = new ShoppingCartViewModel();
        compositeDisposable = new CompositeDisposable();

        shoppingListItems = (List<ShoppingListItem>) getIntent().getSerializableExtra("shoppingListItems");
        setShoppingListOptions(shoppingListItems);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bind();
    }

    private void bind() {
        double oldShoppingCartTotalValue = Double.valueOf(totalPrice.getText().toString());
        compositeDisposable.add(
                shoppingCartViewModel.updateShoppingCartTotalPrice(oldShoppingCartTotalValue, shoppingListItems)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .subscribe(this::setShoppingTotalPrice)
        );
    }

    private void setShoppingListOptions(List<ShoppingListItem> shoppingListItems) {
        shoppingCartListAdapter = new ShoppingCartListAdapter(this, R.id.shoppingListOptions, shoppingListItems);
        shoppingCartList.setAdapter(shoppingCartListAdapter);
    }


    private void setShoppingTotalPrice(Double newTotalPrice) {
        totalPrice.setText(String.format("%.2f", newTotalPrice));
    }

    public void closeCartAndGoToPayment(View view) {
    }
}
