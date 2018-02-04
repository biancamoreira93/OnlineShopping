package com.example.biancamoreira.onlineshopping;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ShoppingListAdapter extends ArrayAdapter<ShoppingListItem> {

    public ShoppingListAdapter(@NonNull Context context, int resource, @NonNull List<ShoppingListItem> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        View view = convertView;

        if (view == null) {
            view = inflateView();
            TextView textView = view.findViewById(R.id.textShoppingItem);
            ImageView imageView = view.findViewById(R.id.imageViewShoppingItem);
            holder = new ViewHolder(imageView, textView);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        ShoppingListItem shoppingListItem = getItem(position);
        holder.setText(shoppingListItem.getItemName());

        return view;
    }

    private View inflateView() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return layoutInflater.inflate(R.layout.linear_layout_shopping_list, null);
    }

    private class ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(ImageView imageView, TextView textView) {
            this.imageView = imageView;
            this.textView = textView;
        }

        public void setText(String text) {
            this.textView.setText(text);
        }
    }
}
