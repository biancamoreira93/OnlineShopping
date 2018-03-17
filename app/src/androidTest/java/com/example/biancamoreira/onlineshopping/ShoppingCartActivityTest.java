package com.example.biancamoreira.onlineshopping;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.example.biancamoreira.onlineshopping.cartUtils.Cart;
import com.example.biancamoreira.onlineshopping.domain.ShoppingItem;
import com.example.biancamoreira.onlineshopping.shoppingCart.ShoppingCartActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ShoppingCartActivityTest {

    @Rule
    public ActivityTestRule<ShoppingCartActivity> shoppingCartActivityActivityTestRule = new ActivityTestRule<ShoppingCartActivity>(ShoppingCartActivity.class, false, false);

    @Test
    public void initiatesShoppingCartWithOneItem() {
        ShoppingItem shoppingItem = new ShoppingItem("Blusa Manga Longa", "", "", "56.90", true, 10);
        Intent intent = new Intent();
        intent.putExtra("shoppingItem", shoppingItem);
        shoppingCartActivityActivityTestRule.launchActivity(intent);

        String expectedTotalPriceText = "56.90";
        onView(allOf(withId(R.id.totalPriceText), withText(expectedTotalPriceText))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.closeShoppingCart), withText(R.string.payment))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.continueShopping), withText(R.string.continue_shopping))).check(matches(isDisplayed()));
    }

    @Test
    public void initiatesEmptyShoppingCart() {
        shoppingCartActivityActivityTestRule.launchActivity(new Intent());

        onView(withId(R.id.priceLayout)).check(matches(not(isDisplayed())));
        onView(allOf(withId(R.id.emptyCartText), withText(R.string.empty_shopping_cart_text))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.closeShoppingCart), withText(R.string.payment))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.continueShopping), withText(R.string.continue_shopping))).check(matches(isDisplayed()));
    }

}
