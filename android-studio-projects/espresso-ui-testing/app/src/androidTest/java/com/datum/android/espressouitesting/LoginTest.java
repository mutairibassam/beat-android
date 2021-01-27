package com.datum.android.espressouitesting;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.*;

@RunWith(AndroidJUnit4.class)
public class LoginTest {

    //    private String stringToBetyped;


    //    @Before
    //    public void initValidString() {
    //        // Specify a valid string.
    //        stringToBetyped = "Espresso";
    //    }


    /**
     * @Rule provides funcitonal testing for a specific single activity
     *
     */

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void checkText_UsernameAndPassword() {

        onView(withId(R.id.et_username))
                .perform(typeText("mutairibassam@gmail.com"));
        onView(withId(R.id.et_password))
                .perform(typeText("12345"), closeSoftKeyboard());


        onView(withId(R.id.et_username))
                .check(matches(withText("mutairibassam@gmail.com")));
        onView(withId(R.id.et_password))
                .check(matches(withText("12345")));

    }

}
