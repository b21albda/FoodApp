package com.example.foodapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodapp.DetailActivity;
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

        recyclerView = view.findViewById(R.id.home_recyclerView);
        adapter = new FoodAdapter(foods, this::onItemClicked);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        new JsonTask(this).execute("https://mobprog.webug.se/json-api?login=b21albda");
        // Inflate the layout for this fragment
        return view;
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