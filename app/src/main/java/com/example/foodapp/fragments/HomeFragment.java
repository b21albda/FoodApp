package com.example.foodapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.JsonTask;
import com.example.foodapp.R;
import com.example.foodapp.SelectListener;
import com.example.foodapp.activities.DetailActivity;
import com.example.foodapp.adapters.FoodAdapter;
import com.example.foodapp.data.Food;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements JsonTask.JsonTaskListener, SelectListener {

    private List<Food> foods = new ArrayList<>();
    private FoodAdapter adapter;
    private SharedPreferences pref;
    private AutoCompleteTextView sortBox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        pref = getActivity().getSharedPreferences("sort", Context.MODE_PRIVATE);

        sortBox = view.findViewById(R.id.sort_box);
        String sort = pref.getString("type", null);
        if (sort != null) {
            sortBox.setText(sort);
        }

        sortBox.setOnItemClickListener((adapterView, view1, i, l) -> {
            TextView tv = view1.findViewById(R.id.tv_option);
            sortBy(tv.getText().toString());
        });

        RecyclerView recyclerView = view.findViewById(R.id.home_recyclerView);
        adapter = new FoodAdapter(foods, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        new JsonTask(this).execute("https://mobprog.webug.se/json-api?login=b21albda");

        // Inflate the layout for this fragment
        return view;
    }

    private void sortBy(String sort) {
        Log.d("TAG", "sortBy: " + sort);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString("type", sort);
        editor.apply();

        List<Food> sorted = new ArrayList<>();
        if (sort.equals("Sort by")){
            sorted = foods;
        } else {
            for (Food food : foods) {
                if (food.getCategory().equals(sort) || food.getAuxdata().getType().equals(sort))
                    sorted.add(food);
            }
        }

        adapter.setFoods(sorted);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPostExecute(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Food>>() {}.getType();
        foods = gson.fromJson(json, type);
        adapter.setFoods(foods);
        adapter.notifyDataSetChanged();
        // Get the saved sort type and apply it
        String sort = pref.getString("type", null);
        if (sort != null) {
            sortBy(sort);
        }
    }

    @Override
    public void onItemClicked(Food food) {
        Intent intent = new Intent(this.getContext(), DetailActivity.class);
        intent.putExtra("food", food);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        String[] options = getResources().getStringArray(R.array.sort_options);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this.getContext(), R.layout.dropdown_item, options);
        sortBox.setAdapter(arrayAdapter);
    }
}