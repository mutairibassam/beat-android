package com.datum.android.espressouitesting;

import android.widget.EditText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.datum.android.espressouitesting.MessageActivity.buildToastMessage;

@RunWith(AndroidJUnit4ClassRunner.class)
public class CustomDialogTest {

    @Rule
    public ActivityScenarioRule<MessageActivity> mActivityTestRule =
            new ActivityScenarioRule<>(MessageActivity.class);

    @Test
    public void test_custom_dialog_user_input() {


        String EXPECTED_NAME = "Bassam";

        onView(withId(R.id.dialog)).perform(click());

        onView(withText("Enter a name")).check(matches(isDisplayed()));

        onView(withText("OK")).perform(click());

        onView(withText("Enter a name")).check(matches(isDisplayed()));

        onView(withId(R.id.et_input)).perform(typeText(EXPECTED_NAME));

        onView(withText("OK")).perform(click());

        onView(withText("Enter a name")).check(doesNotExist());

        onView(withId(R.id.tv_name)).check(matches(withText(EXPECTED_NAME)));

        onView(withText("Your name is " + EXPECTED_NAME))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

    }

}