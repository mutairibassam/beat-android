package com.datum.android.espressouitesting;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class MessageActivitySpinnerTest {

    @Test
    public void test_Adapter_view_simple_Test() {
        ActivityScenario<MessageActivity> scenario = ActivityScenario.launch(MessageActivity.class);

        onView(withId(R.id.spinner)).perform(click());

        onView(withId(R.id.spinner)).check(matches(withText(containsString("Bassam"))));

//        onData(allOf(is(instanceOf(String.class)), is("Khalid"))).perform(click());

    }
}
