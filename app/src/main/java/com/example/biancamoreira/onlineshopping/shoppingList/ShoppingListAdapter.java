package com.example.biancamoreira.onlineshopping.shoppingList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.biancamoreira.onlineshopping.R;
import com.example.biancamoreira.onlineshopping.domain.ShoppingItem;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListAdapter extends ArrayAdapter<ShoppingItem> implements Filterable {

    private List<ShoppingItem> shoppingItems;
    private List<ShoppingItem> shoppingItemsFilter;
    private ShoppingItemFiltered filtered;
    private Context context;

    public ShoppingListAdapter(@NonNull Context context, int resource, @NonNull List<ShoppingItem> objects) {
        super(context, resource);
        this.context = context;
        this.shoppingItems = objects;
        this.shoppingItemsFilter = objects;
    }

    @Override
    public int getCount() {
        return shoppingItems.size();
    }

    @Override
    public ShoppingItem getItem(int position) {
        return shoppingItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return shoppingItems.get(position).hashCode();
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

        ShoppingItem shoppingItem = getItem(position);
        holder.setText(shoppingItem.getItemName());

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
                results.values = shoppingItemsFilter;
                results.count = shoppingItemsFilter.size();
            }
            else {
                ArrayList<ShoppingItem> filteredShoppingItem = new ArrayList<>();

                for (ShoppingItem shoppingItem : shoppingItemsFilter) {
                    if (shoppingItem.getItemName().toUpperCase().contains( constraint.toString().toUpperCase() )) {
                        filteredShoppingItem.add(shoppingItem);
                    }
                }

                results.values = filteredShoppingItem;
                results.count = filteredShoppingItem.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            shoppingItems = (ArrayList<ShoppingItem>) results.values;
            notifyDataSetChanged();
        }
    }
}
