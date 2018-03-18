package com.example.biancamoreira.onlineshopping;

import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.biancamoreira.onlineshopping.domain.ShoppingItem;
import com.example.biancamoreira.onlineshopping.shoppingList.ShoppingListActivity;
import com.example.biancamoreira.onlineshopping.shoppingList.ShoppingListAdapter;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class ShoppingListActivityTest {

    @Rule
    public ActivityTestRule<ShoppingListActivity> shoppingListActivityTestRule = new ActivityTestRule<ShoppingListActivity>(ShoppingListActivity.class);

    @Test
    public void initiatesShoppingListWhenOpeningActivity() {
        onView(allOf(withId(R.id.searchText), withHint(R.string.search_products)));
        checkAllDataDisplayed();
    }

    @Test
    public void filtersShoppingListByShoppingItemName() {
        onView(allOf(withId(R.id.searchText), withHint(R.string.search_products)));
        checkAllDataDisplayed();

        String queryText = "longa";
        onView(withId(R.id.searchText)).perform(replaceText(queryText));
        onView(withId(R.id.shoppingListOptions)).check(matches(not(withShoppingItemView(is(withPrice("90.90"))))));
        onView(withId(R.id.shoppingListOptions)).check(matches(not(withShoppingItemView(is(withPrice("120.90"))))));
        onView(withId(R.id.shoppingListOptions)).check(matches(not(withShoppingItemView(is(withPrice("85.90"))))));
        onView(withId(R.id.shoppingListOptions)).check(matches(not(withShoppingItemView(is(withPrice("75.90"))))));
        onView(withId(R.id.shoppingListOptions)).check(matches(not(withShoppingItemView(is(withPrice("50.90"))))));
        onView(withId(R.id.shoppingListOptions)).check(matches(not(withShoppingItemView(is(withPrice("190.90"))))));
        onView(withId(R.id.shoppingListOptions)).check(matches(not(withShoppingItemView(is(withPrice("130.90"))))));
        onData(allOf(is(instanceOf(ShoppingItem.class)), withPrice("56.90"))).check(matches(isCompletelyDisplayed()));
    }

    private void checkAllDataDisplayed() {
        onData(allOf(is(instanceOf(ShoppingItem.class)), withPrice("56.90"))).check(matches(isCompletelyDisplayed()));
        onData(allOf(is(instanceOf(ShoppingItem.class)), withPrice("90.90"))).check(matches(isCompletelyDisplayed()));
        onData(allOf(is(instanceOf(ShoppingItem.class)), withPrice("120.90"))).check(matches(isCompletelyDisplayed()));
        onData(allOf(is(instanceOf(ShoppingItem.class)), withPrice("85.90"))).check(matches(isCompletelyDisplayed()));
        onData(allOf(is(instanceOf(ShoppingItem.class)), withPrice("75.90"))).check(matches(isCompletelyDisplayed()));
        onData(allOf(is(instanceOf(ShoppingItem.class)), withPrice("50.90"))).check(matches(isCompletelyDisplayed()));
        onData(allOf(is(instanceOf(ShoppingItem.class)), withPrice("190.90"))).check(matches(isCompletelyDisplayed()));
        onData(allOf(is(instanceOf(ShoppingItem.class)), withPrice("130.90"))).check(matches(isCompletelyDisplayed()));
    }

    private static Matcher<ShoppingItem> withPrice(String price) {
        return new TypeSafeMatcher<ShoppingItem>() {
            @Override
            public boolean matchesSafely(ShoppingItem shoppingItem) {
                return price.equals(shoppingItem.getItemPrice());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with string name: " + price);
            }
        };
    }

    private static Matcher<View> withShoppingItemView(final Matcher<ShoppingItem> shoppingItemMatcher) {
        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("with class name: ");
                shoppingItemMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                Adapter adapter = ((AdapterView) view).getAdapter();
                for (int i = 0; i < adapter.getCount(); i++) {
                    if (shoppingItemMatcher.matches(adapter.getItem(i))) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

}
