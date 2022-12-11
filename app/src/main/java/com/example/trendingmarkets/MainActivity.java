package com.example.trendingmarkets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String apikey = "dc9767619c2d4fb3a6d454fe1d6983e1";
    private ListView stockList;
    private String techStocks = "";
    private String airlines = "";
    private String finance = "";
    StringBuilder stockSearch = new StringBuilder();
    private int size;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle arrayBundle = this.getIntent().getExtras();
        ArrayList<String> categories = arrayBundle.getStringArrayList("ArrayList");

        size = categories.size();
        for (String s : categories) {
            stockSearch.append(s).append(",");
        }
        stockSearch.setLength(stockSearch.length() - 1);
        stockList = findViewById(R.id.listView);
        new AsyncDataClass().execute();
    }

    private class AsyncDataClass extends AsyncTask<String, Void, String> {
        HttpURLConnection urlConnection;
        @Override
        protected String doInBackground(String...params ) {

            StringBuilder jsonResult = new StringBuilder();


            try {

                URL url = new URL("https://api.twelvedata.com/time_series?interval=1day&outputsize=1&symbol=" + stockSearch.toString() + "&apikey=" + apikey);
                Log.v("request", url.toString());

                urlConnection = (HttpURLConnection)
                        url.openConnection();
                InputStream in = new
                        BufferedInputStream(urlConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonResult.append(line);
                }
                System.out.println("Returned Json url object " +
                        jsonResult.toString());

            } catch (Exception e) {System.out.println("Err: " + e);}
            finally {
                urlConnection.disconnect();
            }
            return jsonResult.toString();
        }

        @Override
        protected void onPreExecute() {  }

        @Override
        protected void onPostExecute(String result) {

            System.out.println("Result on post execute: " + result);
            List<StockObject> parsedObject = returnParsedJsonObject(result);
            CustomAdapter jsonCustomAdapter = new CustomAdapter(MainActivity.this, parsedObject);
            stockList.setAdapter(jsonCustomAdapter);

            stockList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView arg0, View arg1, int position, long arg3) {
                    Intent i = new Intent(MainActivity.this, StockDetail.class);
                    i.putExtra("position", position);
                    startActivity(i);
                }

            });
        }

    } //end AsyncDataClass class

    private List<StockObject> returnParsedJsonObject(String result){

        List<StockObject> jsonObject = new ArrayList<StockObject>();
        JSONObject resultObject = null;
        JSONArray stocksArray = null;
        StockObject newItemObject = null; //interior object holder

        try {
            resultObject = new JSONObject(result);
            System.out.println("Pre-parsed JSON object " + resultObject.toString());
            // set up json Array to be parsed
            stocksArray = resultObject.toJSONArray(resultObject.names());
            System.out.println("JSON array" + stocksArray);
        } catch (JSONException e) { e.printStackTrace(); }

        for(int i = 0; i < stocksArray.length(); i++){
            System.out.println(resultObject.length());
            JSONObject iteratingStock = null;
            JSONObject metaData = null;
            JSONArray valuesData = null;
            JSONObject valuesDataObject = null;

            try {
                iteratingStock = stocksArray.getJSONObject(i);
                System.out.println(iteratingStock);

                metaData = iteratingStock.getJSONObject("meta");
                valuesData = iteratingStock.getJSONArray("values");
                valuesDataObject = valuesData.getJSONObject(0);
                System.out.println(valuesDataObject);

                //get all data from stream
                String stockTicker = metaData.getString("symbol");
                String stockPrice = valuesDataObject.getString("close");
                String stockVolume = valuesDataObject.getString("volume");

                newItemObject = new StockObject(stockTicker, stockPrice, stockVolume);
                jsonObject.add(newItemObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        System.out.println(jsonObject);
        return jsonObject;
    } //end method
}