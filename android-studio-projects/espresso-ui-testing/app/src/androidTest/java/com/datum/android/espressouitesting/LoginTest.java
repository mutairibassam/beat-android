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
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

import androidx.test.espresso.*;

@RunWith(AndroidJUnit4.class)
public class LoginTest {

    private String email;
    private String password;


        @Before
        public void initValidString() {
            // Specify a valid string.
            email = "mutairibassam@gamil.com";
            password = "123456";
        }


    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void checkText_UsernameAndPassword() {


        onView(withId(R.id.et_username)).perform(typeText(email));
        onView(withId(R.id.et_password)).perform(typeText(password), closeSoftKeyboard());


        onView(withId(R.id.et_username)).check(matches(withText(email)));
        onView(withId(R.id.et_password)).check(matches(withText(password)));

        // Button
         onView(withId(R.id.login_button)).perform(click());

        onView(withId(R.id.messageActivity)).check(matches(isDisplayed()));


    }


    @Test
    public void test_check_button_login_is_typed_correctly() {

        // allOf = to combine multiple matchers
        onView(withId(R.id.login_button)).check(matches(withText("Login")));
    }

}
