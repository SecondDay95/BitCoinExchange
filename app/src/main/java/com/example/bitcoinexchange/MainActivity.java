package com.example.bitcoinexchange;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    String currentValue;
    TextView priceLabel;
    Spinner spinner;

    private final String SECRET_KEY = "MmNkNmExZDdjZDMwNGYyMzgzN2YwOTU5ZjdjYTJiZmVkYTU2ODczMTdmOGI0NWRjOTdjYjkxMzJhM2ViM2YzOQ";
    private final String PUBLIC_KEY = "MDNjZWQ5ZjkwZDBiNDgzYjk4YzQ2OTRjMTIzMDI3Yzc";
    private final String BASE_URL = "https://apiv2.bitcoinaverage.com/indices/global/ticker/BTC";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        priceLabel = findViewById(R.id.priceLabel);
        spinner = findViewById(R.id.currency_spinner);

        // Create an ArrayAdapter using the String array and a spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MainActivity.this,
                R.array.currency_array, R.layout.spinner_item);

        //Setting the view of spinner:
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //Setting a clickListener to spinner:
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentValue = parent.getItemAtPosition(position).toString();
                String finalURL = BASE_URL + currentValue;
                doNetworking(finalURL);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Log.d("Bitcoin", "NothingSelected");
            }
        });

    }
    private void doNetworking (String finalURL) {
        //Create client which can do get, post, etc. request:
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(finalURL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("Bitcoin", "Received JSON: " + response.toString());
                BitCoinModel bitCoinModel = new BitCoinModel();
                try {
                    System.out.println("lastValue = " + response.getString("last"));
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                bitCoinModel.fromJSON(response);
                updateUI(bitCoinModel);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });

    }
    public void updateUI (BitCoinModel bitCoinModel) {
        System.out.println("lastValue = " + bitCoinModel.getLastValue());
        priceLabel.setText(bitCoinModel.getLastValue());
    }

}
