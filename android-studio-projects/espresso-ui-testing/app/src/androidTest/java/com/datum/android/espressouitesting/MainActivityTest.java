package com.datum.android.espressouitesting;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.MissingFormatArgumentException;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule =
            new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void isActivityInView() {
        onView(withId(R.id.mainActivity)).check(matches(isDisplayed()));
        onView(withId(R.id.login_button)).check(matches(isDisplayed()));

//        onView(withId(R.id.et_username))
//                .perform(typeText("mutairibassam@gmail.com"));
//        onView(withId(R.id.et_password))
//                .perform(typeText("12345"), closeSoftKeyboard());
//
//
//        onView(withId(R.id.et_username))
//                .check(matches(withText("mutairibassam@gmail.com")));
//        onView(withId(R.id.et_password))
//                .check(matches(withText("12345")));

//        onView(withId(R.id.login_button)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }

    @Test
    public void test_isTitleTestDisplayed() {
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));

    }
}