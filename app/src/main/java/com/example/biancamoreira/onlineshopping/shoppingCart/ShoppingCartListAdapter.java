package com.example.biancamoreira.onlineshopping.shoppingCart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.biancamoreira.onlineshopping.OnSwipeTouchListener;
import com.example.biancamoreira.onlineshopping.R;
import com.example.biancamoreira.onlineshopping.domain.ShoppingItem;

import java.util.List;

public class ShoppingCartListAdapter extends ArrayAdapter<ShoppingItem> {

    List<ShoppingItem> shoppingItems;
    OnDataChangeListener onDataChangeListener;

    public ShoppingCartListAdapter(@NonNull Context context, int resource, @NonNull List<ShoppingItem> shoppingItems) {
        super(context, resource, shoppingItems);
        this.shoppingItems = shoppingItems;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        View view = convertView;

        if (view == null) {
            view = inflateView();
            viewHolder = initViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        setView(position, viewHolder, view);

        return view;
    }

    void setDataChangedListener(OnDataChangeListener onDataChangeListener) {
        this.onDataChangeListener = onDataChangeListener;
    }

    private View inflateView() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return layoutInflater.inflate(R.layout.linear_layout_shopping_cart_list, null);
    }

    private ViewHolder initViewHolder(View view) {
        ViewHolder viewHolder;
        TextView textShoppingCartItem = view.findViewById(R.id.textShoppingCartItem);
        TextView textPriceItem = view.findViewById(R.id.priceItemCart);
        TextView deleteItemText = view.findViewById(R.id.deleteItemFromCart);
        ImageView imageViewShoppingCartItem = view.findViewById(R.id.imageViewShoppingCartItem);
        viewHolder = new ViewHolder(textShoppingCartItem, textPriceItem, deleteItemText, imageViewShoppingCartItem);
        return viewHolder;
    }

    private void setView(int position, ViewHolder viewHolder, View view) {
        ShoppingItem shoppingItem = getItem(position);
        viewHolder.setShoppingItemTexts(shoppingItem);
        setListeners(view, shoppingItem);
    }

    private void setListeners(View view, ShoppingItem shoppingItem) {
        View deleteItemFromCart = view.findViewById(R.id.deleteItemFromCart);
        View priceItemCart = view.findViewById(R.id.priceItemCart);

        view.setOnTouchListener(new OnSwipeTouchListener(this.getContext()) {
            @Override
            public void onSwipeLeft() {
                ViewGroup viewGroup = view.findViewById(R.id.shoppgingCartItem);
                TransitionManager.beginDelayedTransition(viewGroup);

                deleteItemFromCart.setVisibility(View.VISIBLE);
                priceItemCart.setVisibility(View.GONE);
            }
        });

        deleteItemFromCart.setOnClickListener(v -> {
            this.shoppingItems.remove(shoppingItem);
            notifyDataSetChanged();
            onDataChangeListener.dataChanged(shoppingItems);
        });
    }

    private class ViewHolder {
        TextView textShoppingCartItem;
        TextView textPriceItem;
        TextView deleteItemText;
        ImageView imageView;

        ViewHolder(TextView textShoppingCartItem, TextView textPriceItem, TextView deleteItemText, ImageView imageView) {
            this.textShoppingCartItem = textShoppingCartItem;
            this.textPriceItem = textPriceItem;
            this.imageView = imageView;
            this.deleteItemText = deleteItemText;
        }

        public void setShoppingItemTexts(ShoppingItem shoppingItem) {
            textShoppingCartItem.setText(shoppingItem.getItemName());
            textPriceItem.setText(String.format("R$ %s", shoppingItem.getItemPrice()));
        }
    }
}
