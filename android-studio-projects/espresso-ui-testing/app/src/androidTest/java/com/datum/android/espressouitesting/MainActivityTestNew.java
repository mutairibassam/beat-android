package com.datum.android.espressouitesting;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTestNew {

    @Test
    public void test_if_the_title_is_displayed() {

        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);

        onView(withId((R.id.tv_title))).check(matches(isDisplayed()));

    }
}
