package com.example.trendingmarkets;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class SelectCategories extends AppCompatActivity {
    private CheckBox techCheck, airlinesCheck, financeCheck;
    private Button checkResults;

    private ArrayList<String> results;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);

        techCheck = findViewById(R.id.check_tech);
        airlinesCheck = findViewById(R.id.check_airlines);
        financeCheck = findViewById(R.id.check_finance);

        checkResults = findViewById(R.id.check_results);

        results = new ArrayList<>();

        techCheck.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if (techCheck.isChecked())
                    results.add("AAPL,MSFT");
                else
                    results.remove("AAPL,MSFT");
            }
        });
        airlinesCheck.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if (airlinesCheck.isChecked())
                    results.add("ULCC,SAVE");
                else
                    results.remove("ULCC,SAVE");
            }
        });
        financeCheck.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if (financeCheck.isChecked())
                    results.add("JPM,BAC");
                else
                    results.remove("JPM,BAC");
            }
        });

        checkResults.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.v("Array", results.toString());

                Intent i = new Intent(SelectCategories.this, MainActivity.class);
                Bundle arrayBundle = new Bundle();

                arrayBundle.putStringArrayList("ArrayList", results);
                i.putExtras(arrayBundle);

                startActivity(i);
            }
        });

    }

}
