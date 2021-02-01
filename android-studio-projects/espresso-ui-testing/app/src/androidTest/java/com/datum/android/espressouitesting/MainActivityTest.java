package com.datum.android.espressouitesting;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    public static final String EMAIL_MESSAGE = "I forgot my password";


    /**
     * @Rule provides functional testing for a specific single activity
     *
     */

    @Rule
    public ActivityScenarioRule<MainActivity> rule = new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void test_isActivityInView() {

        // Check visibility
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));
        onView(withId(R.id.mainActivity)).check(matches(isDisplayed()));
        onView(withId(R.id.login_button)).check(matches(isDisplayed()));

        // Another way to check visibility
//        onView(withId(R.id.login_button)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }

    @Test
    public void test_isInputTypedCorrectly() {

        // Type input
        onView(withId(R.id.et_username))
                .perform(typeText("mutairibassam@gmail.com"));
        onView(withId(R.id.et_password))
                .perform(typeText("12345"), closeSoftKeyboard());

        // Check input
        onView(withId(R.id.et_username))
                .check(matches(withText("mutairibassam@gmail.com")));
        onView(withId(R.id.et_password))
                .check(matches(withText("12345")));
    }

    @Test
    public void test_login_functionality() {

        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));
        onView(withId(R.id.mainActivity)).check(matches(isDisplayed()));
        onView(withId(R.id.login_button)).check(matches(isDisplayed()));

        // Type input
        onView(withId(R.id.et_username))
                .perform(typeText("mutairibassam@gmail.com"));
        onView(withId(R.id.et_password))
                .perform(typeText("12345"), closeSoftKeyboard());

        onView(withId(R.id.login_button)).perform(click());
        onView(withId(R.id.messageActivity)).check(matches(isDisplayed()));

    }





    // ################ Intent ###########################

    @Test
    public void ClickSendEmailButton_SendsEmail() {
        onView(withId(R.id.send_button)).perform(click());
        intended(allOf(
                hasAction(Intent.ACTION_SENDTO),
                hasExtra(Intent.EXTRA_TEXT, EMAIL_MESSAGE)
        ));
    }

    @Before
    public void beforeTestClass() {
        Intents.init();

        // to block all external Intents
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    @After
    public void afterTestClass() {
        Intents.release();
    }

}