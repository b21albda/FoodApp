package com.example.foodapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodapp.data.Food;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private List<Food> favorites = new ArrayList<>();
    private SharedPreferences ref;
    private SharedPreferences.Editor editor;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Food food = getIntent().getParcelableExtra("food");

        getSupportActionBar().setTitle(food.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ref = getSharedPreferences("Favorites", MODE_PRIVATE);
        editor = ref.edit();

        TextView name = findViewById(R.id.detail_name);
        TextView price = findViewById(R.id.detail_price);
        TextView description = findViewById(R.id.detail_description);
        ImageView image = findViewById(R.id.detail_image);
        Button addToCart = findViewById(R.id.add_to_cart);
        ImageButton addToFavorites = findViewById(R.id.add_to_favorites);

        String list = ref.getString("FavoritesList", null);
        Gson gson = new Gson();
        Type type = new TypeToken<List<Food>>() {}.getType();
        favorites = gson.fromJson(list, type);

        // Check if item exist in favorites then change the "addToFavorites" button image
        if (favorites.stream().anyMatch(o -> food.getID().equals(o.getID())))
            addToFavorites.setImageResource(R.drawable.ic_favorites_fill);

        addToCart.setOnClickListener(View -> {
            // Todo Implement add to cart
        });

        addToFavorites.setOnClickListener(View -> {
            // Check if the item already exist in the favorites
            if (favorites.stream().anyMatch(o -> food.getID().equals(o.getID()))) {
                favorites.removeIf(o -> food.getID().equals(o.getID()));
                addToFavorites.setImageResource(R.drawable.ic_favorites);
            } else {
                favorites.add(food);
                addToFavorites.setImageResource(R.drawable.ic_favorites_fill);
            }

            // Add the favorites list to the shared preferences in the form of a json string
            String json = gson.toJson(favorites);
            Log.d("TAG", json);
            editor.putString("FavoritesList", json);
            editor.apply();
        });

        name.setText(food.getName());
        price.setText(food.getCost() + " kr");
        description.setText(food.getAuxdata().getDescription());

        String url = food.getAuxdata().getImg();

        if (!url.isEmpty())
            Picasso.get().load(url).into(image);
    }
}