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

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShoppingListAdapter extends ArrayAdapter<ShoppingItem> implements Filterable {

    private List<ShoppingItem> shoppingItems;
    private List<ShoppingItem> shoppingItemsFilter;
    private Disposable disposable;
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
            ImageView imageShoppingItem = view.findViewById(R.id.imageViewShoppingItem);
            TextView textShoppingItem = view.findViewById(R.id.textShoppingItem);
            TextView textPriceItem = view.findViewById(R.id.textShoppingItemPrice);
            holder = new ViewHolder(imageShoppingItem, textShoppingItem, textPriceItem);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        ShoppingItem shoppingItem = getItem(position);
        holder.setShoppingItemTexts(shoppingItem);

        return view;
    }

    private View inflateView() {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return layoutInflater.inflate(R.layout.linear_layout_shopping_list, null);
    }

    public void performFiltering(CharSequence constraint) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }

        disposable = shoppingItemsObservable(constraint)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::publishResults);
    }

    @NonNull
    private Observable<ArrayList<ShoppingItem>> shoppingItemsObservable(CharSequence constraint) {
        ArrayList<ShoppingItem> filteredShoppingItem = new ArrayList<>();

        for (ShoppingItem shoppingItem : shoppingItemsFilter) {
            if (shoppingItem.getItemName().toUpperCase().contains(constraint.toString().toUpperCase())) {
                filteredShoppingItem.add(shoppingItem);
            }
        }

        return Observable.just(filteredShoppingItem);
    }

    protected void publishResults(List<ShoppingItem> shoppingItems) {
        this.shoppingItems = shoppingItems;
        notifyDataSetChanged();
    }

    private class ViewHolder {
        ImageView imageShoppingItem;
        TextView textShoppingItem;
        TextView texPriceItem;

        ViewHolder(ImageView imageShoppingItem, TextView textShoppingItem, TextView textPriceItem) {
            this.textShoppingItem = textShoppingItem;
            this.imageShoppingItem = imageShoppingItem;
            this.texPriceItem = textPriceItem;
        }

        void setShoppingItemTexts(ShoppingItem shoppingItem) {
            this.textShoppingItem.setText(shoppingItem.getItemName());
            this.texPriceItem.setText(String.format("R$ %s", shoppingItem.getItemPrice()));
        }
    }
}
