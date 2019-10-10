package com.example.bitcoinexchange;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BitCoinModelTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void fromJSON() {
        BitCoinModel bitCoinModel = new BitCoinModel();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("last", "12345");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        bitCoinModel.fromJSON(jsonObject);
        Assert.assertEquals(bitCoinModel.getLastValue(), "12345");
    }
}