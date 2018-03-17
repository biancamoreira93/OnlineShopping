package com.example.biancamoreira.onlineshopping.shoppingList;

import com.example.biancamoreira.onlineshopping.domain.ShoppingItem;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ShoppingListViewModelTest {

    @Mock
    private ShoppingListDataModel dataModel;

    private ShoppingListViewModel viewModel;

    @Before
    public void setUp() {
        initMocks(this);
        viewModel = new ShoppingListViewModel();
    }

    @Test
    public void shouldReturnListOfShoppingItemsObservableForDataModel() throws Exception {
        List<ShoppingItem> shoppingItems = Arrays.asList(
                new ShoppingItem("Blusa Manga Longa", "", "", "56.90", true, 10),
                new ShoppingItem("Blusa Manga Curta", "", "", "90.90", true, 9),
                new ShoppingItem("Blusa Listrada", "", "", "120.90", true, 8),
                new ShoppingItem("Blusa Manta", "", "", "90.90", true, 7));
        Observable<List<ShoppingItem>> givenObservable = Observable.fromArray(shoppingItems);

        when(dataModel.getShoppingItems()).thenReturn(givenObservable);
    }
}