package com.datum.android.espressouitesting;

import android.app.Activity;
import android.app.Instrumentation;

//import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

//import static androidx.test.espresso.intent.Intents.intending;
//import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
//import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
//import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class IntentTestFromMainActivityToMessageActivity {

    // import intent lib
    // androidTestImplementation 'androidx.test.espresso:espresso-intents:3.1.0'


    // IntentsTestRule
    // This rule is an extension of the ActivityTestRule, which initializes Intents before
    // each Espresso test (@Test) is run and releases the Intent after each test is run.
    // The associated activity is terminated after each test.

//    @Rule
//    public IntentsTestRule<MainActivity> intentsTestRule =
//            new IntentsTestRule<>(MainActivity.class);



    //  @Before - stubAllExternalIntents()
    //  As mentioned in the comment in the code snippet below, by default Espresso Intent
    //  does not stub any Intents; instead, stubbing must be set up each time a test is run.
    //  The method stubAllExternalIntents() makes sure all external Intents are blocked.

//    @Before
//    public void stubAllExternalIntents() {
//        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
//    }

//    @Test
//    public void sendUsernameAbdPassword() {
//        intending(hasComponent(hasShortClassName(".MessageActivity"))).respondWith(
//                new Instrumentation.ActivityResult(Activity.RESULT_OK,
//                        MessageActivity.createResultData("mutairibassam@gmail.com"))
//        );
//    }

}