package com.datum.android.espressouitesting;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;

//import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.core.internal.deps.guava.collect.Iterables;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.GrantPermissionRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

//import static androidx.test.espresso.intent.Intents.intending;
//import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
//import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
//import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.ext.truth.content.IntentSubject.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class IntentTestFromMainActivityToMessageActivity {

    private static final String VALID_PHONE_NUMBER = "123-345-6789";
    private static final Uri INTENT_DATA_PHONE_NUMBER = Uri.parse("tel:" + VALID_PHONE_NUMBER);
    @Rule public GrantPermissionRule grantPermissionRule = GrantPermissionRule.grant("android.permission.CALL_PHONE");

    @Rule
    public ActivityScenarioRule<MessageActivity> intentsTestRule =
            new ActivityScenarioRule<>(MessageActivity.class);

    @Before
    public void stubAllExternalIntents() {
        Intents.init();
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    @After
    public void afterTesting() {
        Intents.release();
    }

//    @Test
//    public void typeNumber_ValidInput_InitiatesCall() {
//
//        onView(withId(R.id.edit_text_caller_number))
//                .perform(typeText(VALID_PHONE_NUMBER), closeSoftKeyboard());
//        onView(withId(R.id.button_call_number)).perform(click());
//
//        intended(allOf(
//                hasAction(Intent.ACTION_CALL),
//                hasData(INTENT_DATA_PHONE_NUMBER)));
//    }

    @Test
    public void typeNumber_ValidInput_InitiatesCall_truth() {

        onView(withId(R.id.edit_text_caller_number))
                .perform(typeText(VALID_PHONE_NUMBER), closeSoftKeyboard());
        onView(withId(R.id.button_call_number)).perform(click());

        Intent receivedIntent = Iterables.getOnlyElement(Intents.getIntents());
        assertThat(receivedIntent).hasAction(Intent.ACTION_CALL);
        assertThat(receivedIntent).hasData(INTENT_DATA_PHONE_NUMBER);
    }


    @Test
    public void pickContactButton_click_SelectsPhoneNumber() {
        intending(hasComponent(hasShortClassName(".ContactActivity")))
                .respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK,
                        ContactActivity.createResultData(VALID_PHONE_NUMBER)));

        onView(withId(R.id.button_pick_contact)).perform(click());

        onView(withId(R.id.edit_text_caller_number))
                .check(matches(withText(VALID_PHONE_NUMBER)));
    }

}