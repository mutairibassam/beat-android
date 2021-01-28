package com.datum.android.espressouitesting;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class CustomDialogTest {

    @Rule
    public ActivityScenarioRule<MessageActivity> mActivityTestRule =
            new ActivityScenarioRule<>(MessageActivity.class);

    @Test
    public void test_custom_dialog_user_input() {

        ActivityScenario<MessageActivity> rule = ActivityScenario.launch(MessageActivity.class);

        String EXPECTED_NAME = "Bassam";

        onView(withId(R.id.dialog)).perform(click());

        onView(withText("Enter a name")).check(matches(isDisplayed()));

        onView(withText("OK")).perform(click());

        onView(withText("Enter a name")).check(matches(isDisplayed()));

    }
}