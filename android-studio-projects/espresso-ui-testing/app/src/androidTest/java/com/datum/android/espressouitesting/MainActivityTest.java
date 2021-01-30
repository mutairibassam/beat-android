package com.datum.android.espressouitesting;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.MissingFormatArgumentException;

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
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static final String EMAIL_MESSAGE = "I forgot my password";


//    @Before
//    public void stubAllExternalIntents() {
//
//    }
//
//    @After
//    public void afterTesting() {
//        Intents.release();
//    }


    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void isActivityInView() {
        onView(withId(R.id.mainActivity)).check(matches(isDisplayed()));
        onView(withId(R.id.login_button)).check(matches(isDisplayed()));

        onView(withId(R.id.et_username))
                .perform(typeText("mutairibassam@gmail.com"));
        onView(withId(R.id.et_password))
                .perform(typeText("12345"), closeSoftKeyboard());


        onView(withId(R.id.et_username))
                .check(matches(withText("mutairibassam@gmail.com")));
        onView(withId(R.id.et_password))
                .check(matches(withText("12345")));

        onView(withId(R.id.login_button)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }

    @Test
    public void test_isTitleTestDisplayed() {
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));

    }

    @Test
    public void ClickSendEmailButton_SendsEmail() {
        onView(withId(R.id.send_button)).perform(click());
        intended(allOf(
                hasAction(Intent.ACTION_SENDTO),
                hasExtra(Intent.EXTRA_TEXT, EMAIL_MESSAGE)
        ));
    }

    @Before
    public static void setUp() throws Exception {
        Intents.init();

        // to block all external Intents
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    @After
    public static void afterClass() throws Exception {
        Intents.release();
    }

}