package com.example.foodapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.example.foodapp.activities.DetailActivity;
import com.example.foodapp.JsonTask;
import com.example.foodapp.R;
import com.example.foodapp.SelectListener;
import com.example.foodapp.adapters.FoodAdapter;
import com.example.foodapp.data.Food;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements JsonTask.JsonTaskListener, SelectListener {

    private RecyclerView recyclerView;
    private List<Food> foods = new ArrayList<>();
    private FoodAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        AutoCompleteTextView sortBox = view.findViewById(R.id.sort_box);
        String[] t = {"Sort by", "Beverage", "Food", "Wine", "Beer", "Meat"};
        ArrayAdapter test = new ArrayAdapter(this.getContext(), R.layout.dropdown_item, t);
        sortBox.setAdapter(test);
        sortBox.setOnItemClickListener((adapterView, view1, i, l) -> {
            TextView tv = view1.findViewById(R.id.tv_option);
            sortBy(tv.getText().toString());
        });

        recyclerView = view.findViewById(R.id.home_recyclerView);
        adapter = new FoodAdapter(foods, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        new JsonTask(this).execute("https://mobprog.webug.se/json-api?login=b21albda");
        // Inflate the layout for this fragment
        return view;
    }

    private void sortBy(String sortBy) {
        Log.d("TAG", "sortBy: " + sortBy);

    }

    @Override
    public void onPostExecute(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Food>>() {}.getType();
        foods = gson.fromJson(json, type);
        adapter.setFoods(foods);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClicked(Food food) {
        Intent intent = new Intent(this.getContext(), DetailActivity.class);
        intent.putExtra("food", food);
        startActivity(intent);
    }
}