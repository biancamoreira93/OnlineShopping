package com.example.biancamoreira.onlineshopping;

import android.support.test.rule.ActivityTestRule;

import com.example.biancamoreira.onlineshopping.model.ShoppingItem;
import com.example.biancamoreira.onlineshopping.shoppingList.ShoppingListActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class ShoppingListActivityTest {

    @Rule
    public ActivityTestRule<ShoppingListActivity> shoppingListActivityTestRule = new ActivityTestRule<ShoppingListActivity>(ShoppingListActivity.class);

    @Test
    public void initiatesShoppingListWhenOpeningActivity() {
        onView(allOf(withId(R.id.searchText), withHint(R.string.search_products)));
        onData(allOf(is(instanceOf(ShoppingItem.class)), withPrice("90.90"))).check(matches(isCompletelyDisplayed()));
    }

    private static Matcher<ShoppingItem> withPrice(String price){
        return new TypeSafeMatcher<ShoppingItem>(){
            @Override
            public boolean matchesSafely(ShoppingItem shoppingItem) {
                return price.equals(shoppingItem.getItemPrice());
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }

}
