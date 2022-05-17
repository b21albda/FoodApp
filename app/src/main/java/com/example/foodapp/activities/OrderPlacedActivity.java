package com.example.foodapp.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.R;

public class OrderPlacedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placed);

        getSupportActionBar().setTitle("Order placed");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        Button ok = findViewById(R.id.btn_confirm);

        ok.setOnClickListener(View -> {
            finish();
        });
    }
}