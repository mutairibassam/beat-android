package com.datum.android.recyclerviewapp;

import android.content.Context;
import android.content.res.Resources;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.test.InstrumentationRegistry;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.datum.android.recyclerviewapp.oldlayout.OldMainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.removeGlobalAssertion;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class RecyclerviewTest {

    private static final int ITEM_INDEX = 1;
    @Rule
    public ActivityScenarioRule<OldMainActivity> rule =
            new ActivityScenarioRule<>(OldMainActivity.class);

    private final Context context = ApplicationProvider.getApplicationContext();
    OldMainActivity getActivity = new OldMainActivity(context);



    @Test
    public void test_recyclerview_display() {
        onView(ViewMatchers.withId(R.id.recyclerview))
                .perform(RecyclerViewActions.scrollTo(
                        hasDescendant(withText("bassam"))
                ));
    }


    @Test
    public void scrollToItemBelowFold_checkItsText() {



        // First, scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.recyclerview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(ITEM_INDEX,
                        click()));

        // Match the text in an item below the fold and check that it's displayed.
        onView(withText("mohammad@gmail.com")).check(matches(isDisplayed()));

        String s = ApplicationProvider.getApplicationContext().getResources().getString(R.string.bassam);


    }
}
