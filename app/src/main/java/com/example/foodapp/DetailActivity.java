package com.example.foodapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodapp.data.Food;
import com.example.foodapp.database.DatabaseHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Food food = getIntent().getParcelableExtra("food");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(food.getName());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Grab the views
        TextView name = findViewById(R.id.detail_name);
        TextView price = findViewById(R.id.detail_price);
        TextView description = findViewById(R.id.detail_description);
        ImageView image = findViewById(R.id.detail_image);
        Button addToCart = findViewById(R.id.add_to_cart);
        ImageButton addToFavorites = findViewById(R.id.add_to_favorites);

        DatabaseHelper helper = new DatabaseHelper(this);
        // Check if item exist in favorites then change the "addToFavorites" button image
        if (helper.exists(food.getID()))
            addToFavorites.setImageResource(R.drawable.ic_favorites_fill);

        addToCart.setOnClickListener(View -> {
            // Todo Implement add to cart
        });

        addToFavorites.setOnClickListener(View -> {
            // Check if the item already exist in the favorites
            if (helper.exists(food.getID())) {
                helper.delete(food.getID());
                addToFavorites.setImageResource(R.drawable.ic_favorites);
            } else {
                helper.insert(food);
                addToFavorites.setImageResource(R.drawable.ic_favorites_fill);
            }
        });

        name.setText(food.getName());
        price.setText(food.getCost() + " kr");
        description.setText(food.getAuxdata().getDescription());

        String url = food.getAuxdata().getImg();

        if (!url.isEmpty())
            Picasso.get().load(url).into(image);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}