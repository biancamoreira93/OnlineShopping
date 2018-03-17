package com.example.biancamoreira.onlineshopping.shoppingCart;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.biancamoreira.onlineshopping.R;
import com.example.biancamoreira.onlineshopping.cartUtils.Cart;
import com.example.biancamoreira.onlineshopping.cartUtils.CartHelper;
import com.example.biancamoreira.onlineshopping.domain.ShoppingItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ShoppingCartActivity extends AppCompatActivity implements OnDataChangeListener {

    @BindView(R.id.shoppingCartList)
    ListView shoppingCartList;

    @BindView(R.id.totalPriceText)
    TextView totalPrice;

    private ShoppingCartListAdapter shoppingCartListAdapter;
    private CompositeDisposable compositeDisposable;
    private ShoppingCartViewModel shoppingCartViewModel;
    private ShoppingItem shoppingItem;
    private Cart cart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        ButterKnife.bind(this);

        shoppingCartViewModel = new ShoppingCartViewModel();
        compositeDisposable = new CompositeDisposable();

        shoppingItem = (ShoppingItem) getIntent().getSerializableExtra("shoppingItem");
        cart = CartHelper.getCart();

        if(shoppingItem != null) { cart.addShoppingItem(shoppingItem); }

        setShoppingListOptions(cart.getShoppingItems());
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataChanged(cart.getShoppingItems());
    }

    public void continueShopping(View view) {
        setResult(1);
        finish();
    }

    public void closeCartAndGoToPayment(View view) {
    }

    @Override
    public void dataChanged(List<ShoppingItem> shoppingItems) {
        TextView textView = findViewById(R.id.emptyCartText);
        LinearLayout priceLayout = findViewById(R.id.priceLayout);

        if (shoppingItems.isEmpty()) {
            textView.setVisibility(View.VISIBLE);
            shoppingCartList.setVisibility(View.GONE);
            priceLayout.setVisibility(View.GONE);
        } else {
            textView.setVisibility(View.GONE);
            shoppingCartList.setVisibility(View.VISIBLE);
            priceLayout.setVisibility(View.VISIBLE);
            bindTotalPriceText(shoppingItems);
        }
    }

    private void bindTotalPriceText(List<ShoppingItem> shoppingItems) {
        compositeDisposable.add(
                shoppingCartViewModel.updateShoppingCartTotalPrice(shoppingItems)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.computation())
                        .subscribe(this::setShoppingTotalPrice)
        );
    }

    private void setShoppingListOptions(List<ShoppingItem> shoppingItems) {
        shoppingCartListAdapter = new ShoppingCartListAdapter(this, R.id.shoppingListOptions, shoppingItems);
        shoppingCartListAdapter.setDataChangedListener(this);
        shoppingCartList.setAdapter(shoppingCartListAdapter);
    }

    @SuppressLint("DefaultLocale")
    private void setShoppingTotalPrice(Double newTotalPrice) {
        totalPrice.setText(String.format("%.2f", newTotalPrice));
    }
}
