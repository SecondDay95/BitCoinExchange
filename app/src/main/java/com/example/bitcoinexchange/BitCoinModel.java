package com.example.bitcoinexchange;

import org.json.JSONException;
import org.json.JSONObject;

public class BitCoinModel {

    private String lastValue;

    public BitCoinModel fromJSON(JSONObject jsonObject) {
        try {
            BitCoinModel bitCoinModel = new BitCoinModel();
            System.out.println("JSON: " + jsonObject.toString());
            lastValue = jsonObject.getString("last");
            System.out.println("lastValue = " + lastValue);
            return bitCoinModel;
        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getLastValue() {
        return lastValue;
    }
}
