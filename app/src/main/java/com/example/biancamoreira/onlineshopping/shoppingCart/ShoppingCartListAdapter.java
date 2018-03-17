package com.example.biancamoreira.onlineshopping.shoppingCart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.biancamoreira.onlineshopping.OnSwipeTouchListener;
import com.example.biancamoreira.onlineshopping.R;
import com.example.biancamoreira.onlineshopping.model.ShoppingItem;

import java.util.List;

class ShoppingCartListAdapter extends ArrayAdapter<ShoppingItem> {
    public ShoppingCartListAdapter(@NonNull Context context, int resource, @NonNull List<ShoppingItem> objects) {
        super(context, resource, objects);
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

    @NonNull
    private ViewHolder initViewHolder(View view) {
        ViewHolder viewHolder;TextView textShoppingCartItem = view.findViewById(R.id.textShoppingCartItem);
        TextView deleteItemText = view.findViewById(R.id.deleteItemFromCart);
        ImageView imageViewShoppingCartItem = view.findViewById(R.id.imageViewShoppingCartItem);
        viewHolder = new ViewHolder(textShoppingCartItem, deleteItemText, imageViewShoppingCartItem);
        return viewHolder;
    }

    private void setView(int position, ViewHolder viewHolder, View view) {
        ShoppingItem shoppingItem = getItem(position);
        viewHolder.setText(shoppingItem.getItemName());
        setListener(view);
    }

    private View inflateView() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return layoutInflater.inflate(R.layout.linear_layout_shopping_cart_list, null);
    }

    private void setListener(View view) {
        view.setOnTouchListener(new OnSwipeTouchListener(this.getContext()) {
            @Override
            public void onSwipeLeft() {
                view.findViewById(R.id.deleteItemFromCart).setVisibility(View.VISIBLE);
                view.findViewById(R.id.priceItemCart).setVisibility(View.GONE);
            }
        });
    }

    private class ViewHolder {
        TextView textView;
        TextView deleteItemText;
        ImageView imageView;

        ViewHolder(TextView textView, TextView deleteItemText, ImageView imageView) {
            this.textView = textView;
            this.imageView = imageView;
            this.deleteItemText = deleteItemText;
        }

        public void setText(String text) {
            textView.setText(text);
        }
    }
}
