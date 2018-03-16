package com.example.biancamoreira.onlineshopping.shoppingCart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.biancamoreira.onlineshopping.R;
import com.example.biancamoreira.onlineshopping.cartUtils.Cart;
import com.example.biancamoreira.onlineshopping.cartUtils.CartHelper;
import com.example.biancamoreira.onlineshopping.model.ShoppingItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ShoppingCartActivity extends AppCompatActivity {

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

        cart.addShoppingItem(shoppingItem);
        setShoppingListOptions(cart.getShoppingItems());
    }

    @Override
    protected void onResume() {
        super.onResume();
        bind();
    }

    private void bind() {
        double oldShoppingCartTotalValue = Double.valueOf(totalPrice.getText().toString());
        compositeDisposable.add(
                shoppingCartViewModel.updateShoppingCartTotalPrice(oldShoppingCartTotalValue, cart.getShoppingItems())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.computation())
                .subscribe(this::setShoppingTotalPrice)
        );
    }

    private void setShoppingListOptions(List<ShoppingItem> shoppingItems) {
        shoppingCartListAdapter = new ShoppingCartListAdapter(this, R.id.shoppingListOptions, shoppingItems);
        shoppingCartList.setAdapter(shoppingCartListAdapter);
    }


    private void setShoppingTotalPrice(Double newTotalPrice) {
        /*TODO Move it to ViewModel*/
        totalPrice.setText(String.format("%.2f", newTotalPrice));
    }

    public void closeCartAndGoToPayment(View view) {
    }

    public void continueShopping(View view) {
        setResult(1);
        finish();
    }
}
