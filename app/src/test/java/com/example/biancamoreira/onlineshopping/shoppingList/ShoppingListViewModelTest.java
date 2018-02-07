package com.example.biancamoreira.onlineshopping.shoppingList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by lbarra on 07/02/18.
 */
public class ShoppingListViewModelTest {

    @Mock
    private ShoppingListDataModel dataModel;

    private ShoppingListViewModel viewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        viewModel = new ShoppingListViewModel();
    }

    @Test
    public void shouldReturnEmptyObservableForEmptyDataModel() throws Exception {
        when(dataModel.getShoppingItems()).thenReturn(Observable.empty());

        assertEquals(Observable.empty(), viewModel.getAvailableShoppingItems());
    }
}