package com.datum.android.espressouitesting;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;

//import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.core.internal.deps.guava.collect.Iterables;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.GrantPermissionRule;

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
public class IntentTestFromMainActivityToMessageActivity {

    private static final String VALID_PHONE_NUMBER = "123-345-6789";

    private static final Uri INTENT_DATA_PHONE_NUMBER = Uri.parse("tel:" + VALID_PHONE_NUMBER);

    @Rule public GrantPermissionRule grantPermissionRule = GrantPermissionRule.grant("android.permission.CALL_PHONE");

    @Rule
    public IntentsTestRule<MessageActivity> intentsTestRule =
            new IntentsTestRule<>(MessageActivity.class);

    @Before
    public void stubAllExternalIntents() {
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    @Test
    public void typeNumber_ValidInput_InitiatesCall() {
        // Types a phone number into the dialer edit text field and presses the call button.
        onView(withId(R.id.edit_text_caller_number))
                .perform(typeText(VALID_PHONE_NUMBER), closeSoftKeyboard());
        onView(withId(R.id.button_call_number)).perform(click());

        // Verify that an intent to the dialer was sent with the correct action, phone
        // number and package. Think of Intents intended API as the equivalent to Mockito's verify.
        intended(allOf(
                hasAction(Intent.ACTION_CALL),
                hasData(INTENT_DATA_PHONE_NUMBER)));
    }

    /**
     * Duplicate typeNumber_ValidInput_InitiatesCall, but using truth assertions
     */
    @Test
    public void typeNumber_ValidInput_InitiatesCall_truth() {
        // Types a phone number into the dialer edit text field and presses the call button.
        onView(withId(R.id.edit_text_caller_number))
                .perform(typeText(VALID_PHONE_NUMBER), closeSoftKeyboard());
        onView(withId(R.id.button_call_number)).perform(click());

        // Verify that an intent to the dialer was sent with the correct action, phone
        // number and package.
        Intent receivedIntent = Iterables.getOnlyElement(Intents.getIntents());
        assertThat(receivedIntent).hasAction(Intent.ACTION_CALL);
        assertThat(receivedIntent).hasData(INTENT_DATA_PHONE_NUMBER);
    }


    @Test
    public void pickContactButton_click_SelectsPhoneNumber() {
        // Stub all Intents to ContactsActivity to return VALID_PHONE_NUMBER. Note that the Activity
        // is never launched and result is stubbed.
        intending(hasComponent(hasShortClassName(".ContactActivity")))
                .respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK,
                        ContactActivity.createResultData(VALID_PHONE_NUMBER)));

        // Click the pick contact button.
        onView(withId(R.id.button_pick_contact)).perform(click());

        // Check that the number is displayed in the UI.
        onView(withId(R.id.edit_text_caller_number))
                .check(matches(withText(VALID_PHONE_NUMBER)));
    }

}