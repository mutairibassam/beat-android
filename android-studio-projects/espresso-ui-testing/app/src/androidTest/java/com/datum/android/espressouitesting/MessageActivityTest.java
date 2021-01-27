package com.datum.android.espressouitesting;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class MessageActivityTest {

//    @Rule
//    public ActivityScenarioRule<MessageActivity> rule =
//            new ActivityScenarioRule<>(MessageActivity.class);

    @Test
    public void isActivityInView() {

        ActivityScenario<MessageActivity> scenario =
                ActivityScenario.launch(MessageActivity.class);

        onView(withId(R.id.messageActivity)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));
        onView(withId(R.id.logout_button)).check(matches(isDisplayed()));

//        onView(withId(R.id.login_button)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }

    @Test
    public void test_navigation_from_MainActivity_to_MesssageActivity_And_Back() {

        ActivityScenario<MainActivity> scenario =
                ActivityScenario.launch(MainActivity.class);

        onView(withId(R.id.login_button)).perform(click());
        onView(withId(R.id.messageActivity)).check(matches(isDisplayed()));

//        onView(withId(R.id.logout_button)).perform(click()); // method 1
        pressBack(); // method 2

        onView(withId(R.id.mainActivity)).check(matches(isDisplayed()));
    }
}