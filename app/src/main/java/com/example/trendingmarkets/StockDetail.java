package com.example.trendingmarkets;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StockDetail extends AppCompatActivity {
    private int mPosition;
    private TextView tickerView;
    private TextView priceView;
    private TextView volumeView;
    private TextView lowView;
    private TextView highView;
    private TextView dateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_detail);

        Intent i = getIntent();
        mPosition = i.getIntExtra("position", 0);

        tickerView = findViewById(R.id.ticker);
        priceView = findViewById(R.id.price);
        volumeView = findViewById(R.id.volume);
        lowView = findViewById(R.id.low);
        highView = findViewById(R.id.high);
        dateView = findViewById(R.id.date);



//        tickerView.setText();
//
//        priceView.setText(getResources().getString(mDataSource.getmQuotePool()
//                .get(mPosition)));
//
//        volumeView.setText(getResources().getString(mDataSource.getmQuotePool()
//                .get(mPosition)));
//
//        lowView.setText(getResources().getString(mDataSource.getmQuotePool()
//                .get(mPosition)));
//
//        highView.setText(getResources().getString(mDataSource.getmQuotePool()
//                .get(mPosition)));
//
//        dateView.setText(getResources().getString(mDataSource.getmQuotePool()
//                .get(mPosition)));

    }


}
