package com.example.bitcoinexchange;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityTest {

    @Rule
    //Launching Main activity before executing any of the tests
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    private MainActivity mainActivity;

    @Before
    public void setUp() throws Exception {
        mainActivity = mainActivityActivityTestRule.getActivity();
    }

    @Test
    public void launchViews() throws Exception {
        View view = mainActivity.findViewById(R.id.currency_spinner);
        View view1 = mainActivity.findViewById(R.id.priceLabel);

        Assert.assertNotNull(view);
        Assert.assertNotNull(view1);
    }

    @Test
    public void testExchangeBitCoin() throws Exception{
        BitCoinModel bitCoinModel = new BitCoinModel();
        onView(withId(R.id.currency_spinner)).perform(ViewActions.click());
        onView(withText("AUD")).perform(ViewActions.click());
        String value = bitCoinModel.getLastValue();
        System.out.println("Value = "+ value);
        Thread.sleep(3000);

        //onView(withId(R.id.priceLabel)).check(ViewAssertions.matches(withText("12795.85")));

    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}