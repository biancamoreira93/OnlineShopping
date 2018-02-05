package com.example.biancamoreira.onlineshopping.shoppingList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.biancamoreira.onlineshopping.R;
import com.example.biancamoreira.onlineshopping.model.ShoppingListItem;

import java.io.FilterWriter;
import java.util.ArrayList;
import java.util.List;

public class ShoppingListAdapter extends BaseAdapter implements Filterable {

    private List<ShoppingListItem> shoppingListItems;
    private List<ShoppingListItem> shoppingListItemsFilter;
    private ShoppingItemFiltered filtered;
    private Context context;

    public ShoppingListAdapter(@NonNull Context context, int resource, @NonNull List<ShoppingListItem> objects) {
        this.context = context;
        this.shoppingListItems = objects;
        this.shoppingListItemsFilter = objects;
    }

    @Override
    public int getCount() {
        return shoppingListItems.size();
    }

    @Override
    public ShoppingListItem getItem(int position) {
        return shoppingListItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return shoppingListItems.get(position).hashCode();
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

    @NonNull
    @Override
    public Filter getFilter() {
        if (filtered == null) {
            filtered = new ShoppingItemFiltered();
        }
        return filtered;
    }

    private View inflateView() {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

    private class ShoppingItemFiltered extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint == null || constraint.length() == 0) {
                results.values = shoppingListItemsFilter;
                results.count = shoppingListItemsFilter.size();
            }
            else {
                ArrayList<ShoppingListItem> filteredShoppingItem = new ArrayList<>();

                for (ShoppingListItem shoppingListItem : shoppingListItemsFilter) {
                    if (shoppingListItem.getItemName().toUpperCase().contains( constraint.toString().toUpperCase() )) {
                        filteredShoppingItem.add(shoppingListItem);
                    }
                }

                results.values = filteredShoppingItem;
                results.count = filteredShoppingItem.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            shoppingListItems = (ArrayList<ShoppingListItem>) results.values;
            notifyDataSetChanged();
        }
    }
}
