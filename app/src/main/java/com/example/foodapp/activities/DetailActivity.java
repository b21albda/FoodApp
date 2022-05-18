package com.example.foodapp.activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.R;
import com.example.foodapp.data.Food;
import com.example.foodapp.database.CartHelper;
import com.example.foodapp.database.FavoritesHelper;
import com.squareup.picasso.Picasso;

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
        TextView size = findViewById(R.id.tv_size);
        ImageView image = findViewById(R.id.detail_image);
        Button addToCart = findViewById(R.id.add_to_cart);
        ImageButton addToFavorites = findViewById(R.id.add_to_favorites);

        FavoritesHelper helper = new FavoritesHelper(this);
        // Check if item exist in favorites then change the "addToFavorites" button image
        if (helper.exists(food.getID()))
            addToFavorites.setImageResource(R.drawable.ic_favorites_fill);

        addToCart.setOnClickListener(View -> {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Item added to cart",
                    Toast.LENGTH_SHORT);
            CartHelper cartHelper = new CartHelper(this);
            if (cartHelper.insert(food) == -1) {
                toast.setText("Something went wrong");
            }

            // Display toast to show a item has been added
            toast.show();
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

        Resources res = getResources();
        name.setText(food.getName());
        price.setText(res.getString(R.string.price, food.getCost()));
        description.setText(food.getAuxdata().getDescription());

        String unit = food.getCategory().equals("Food") ? "g" : "cl";
        size.setText(res.getString(R.string.size, food.getSize(), unit));

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