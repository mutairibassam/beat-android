package com.datum.android.espressouitesting;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.UiObjectNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MenuActivityMenuTest {

    @Test
    public void test_menu_save_button() throws UiObjectNotFoundException {
        ActivityScenario<MenuActivity> scenario = ActivityScenario.launch(MenuActivity.class);

        openActionBarOverflowOrOptionsMenu(
                ApplicationProvider.getApplicationContext());

        // Click the item.
        onView(withText("save")).perform(click());
        onView(withText("This item has been saved")).check(matches(isDisplayed()));


        // UI Automator library

//        UiDevice mDevice = UiDevice.getInstance(getInstrumentation());
//        UiObject snackbarTextView = mDevice.findObject(new UiSelector().text("This item has been saved"));
//
//        if(!snackbarTextView.getText().equals("This item has been saved")) {
//            throw new RuntimeException("No snackbar!");
//        }

    }

    @Test
    public void test_menu_delete_button() {
        ActivityScenario<MenuActivity> scenario = ActivityScenario.launch(MenuActivity.class);

        openActionBarOverflowOrOptionsMenu(
                ApplicationProvider.getApplicationContext());

        onView(withText("delete")).perform(click());
        onView(withText("This item has been deleted")).check(matches(isDisplayed()));

    }
}
