package com.example.john.calculator;

import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by PC on 7/10/2017.
 */
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    private MainActivity mActivity = null;
    private Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(
            MainActivity.class.getName(), null, false);

    private TextView textView;

    @Before
    public void setUp() throws Exception {
        mActivity = mainActivityActivityTestRule.getActivity();

        textView = (TextView) mActivity.findViewById(R.id.input_text_view);
    }

    @Test
    public void sumTest() {
        assertNotNull(textView);
        assertNotNull(mActivity.findViewById(R.id.button1));
        assertNotNull(mActivity.findViewById(R.id.button2));
        assertNotNull(mActivity.findViewById(R.id.button_plus));
        assertNotNull(mActivity.findViewById(R.id.button_equal));
        assertNotNull(mActivity.findViewById(R.id.input_text_view));
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.button_plus)).perform(click());
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.button_equal)).perform(click());

        checkValue("3");

        getInstrumentation().waitForMonitorWithTimeout(monitor, 3000);
    }

    @Test
    public void subtractionTest() {
        assertNotNull(mActivity.findViewById(R.id.button1));
        assertNotNull(mActivity.findViewById(R.id.button2));
        assertNotNull(mActivity.findViewById(R.id.button_subtraction));
        assertNotNull(mActivity.findViewById(R.id.button_equal));
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.button_subtraction)).perform(click());
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.button_equal)).perform(click());

        checkValue("-1");

        getInstrumentation().waitForMonitorWithTimeout(monitor, 3000);
    }

    @Test
    public void multiplicationTest() {
        assertNotNull(mActivity.findViewById(R.id.button1));
        assertNotNull(mActivity.findViewById(R.id.button2));
        assertNotNull(mActivity.findViewById(R.id.button_multiplication));
        assertNotNull(mActivity.findViewById(R.id.button_equal));
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.button_multiplication)).perform(click());
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.button_equal)).perform(click());

        checkValue("2");

        getInstrumentation().waitForMonitorWithTimeout(monitor, 3000);
    }

    @Test
    public void divisionTest() {
        assertNotNull(mActivity.findViewById(R.id.button1));
        assertNotNull(mActivity.findViewById(R.id.button2));
        assertNotNull(mActivity.findViewById(R.id.button_division));
        assertNotNull(mActivity.findViewById(R.id.button_equal));
        onView(withId(R.id.button1)).perform(click());
        onView(withId(R.id.button_division)).perform(click());
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.button_equal)).perform(click());

        checkValue("0.5");

        getInstrumentation().waitForMonitorWithTimeout(monitor, 3000);
    }

    @Test
    public void squareRootTest() {
        assertNotNull(mActivity.findViewById(R.id.button9));
        assertNotNull(mActivity.findViewById(R.id.button_sqr));
        assertNotNull(mActivity.findViewById(R.id.button_equal));
        onView(withId(R.id.button_sqr)).perform(click());
        onView(withId(R.id.button9)).perform(click());
        onView(withId(R.id.button_equal)).perform(click());

        checkValue("3");

        getInstrumentation().waitForMonitorWithTimeout(monitor, 3000);
    }

    @Test
    public void bracketsTest() {
        assertNotNull(mActivity.findViewById(R.id.button9));
        assertNotNull(mActivity.findViewById(R.id.button_sqr));
        assertNotNull(mActivity.findViewById(R.id.button_open_bracket));
        assertNotNull(mActivity.findViewById(R.id.button2));
        assertNotNull(mActivity.findViewById(R.id.button_plus));
        assertNotNull(mActivity.findViewById(R.id.button_equal));
        onView(withId(R.id.button_sqr)).perform(click());
        onView(withId(R.id.button9)).perform(click());
        onView(withId(R.id.button_open_bracket)).perform(click());
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.button_plus)).perform(click());
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.button_close_bracket)).perform(click());
        onView(withId(R.id.button_plus)).perform(click());
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.button_equal)).perform(click());

        checkValue("14");

        getInstrumentation().waitForMonitorWithTimeout(monitor, 3000);
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

    private void checkValue(String value){
        int index = textView.getText().toString().indexOf("=") + 1;
        assertEquals(value, textView.getText().toString().substring(index,
                textView.getText().toString().length()));
    }

}