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
            TextView textView = view.findViewById(R.id.textShoppingCartItem);
            ImageView imageView = view.findViewById(R.id.imageViewShoppingCartItem);
            viewHolder = new ViewHolder(textView, imageView);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        ShoppingItem shoppingItem = getItem(position);
        viewHolder.setText(shoppingItem.getItemName());

        return view;
    }

    private View inflateView() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return layoutInflater.inflate(R.layout.linear_layout_shopping_cart_list, null);
    }

    private class ViewHolder {
        TextView textView;
        ImageView imageView;

        public ViewHolder(TextView textView, ImageView imageView) {
            this.textView = textView;
            this.imageView = imageView;
        }

        public void setText(String text) {
            textView.setText(text);
        }
    }
}
