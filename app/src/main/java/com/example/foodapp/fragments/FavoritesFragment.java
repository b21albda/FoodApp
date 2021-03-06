package com.example.foodapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.SelectListener;
import com.example.foodapp.activities.DetailActivity;
import com.example.foodapp.adapters.FoodAdapter;
import com.example.foodapp.data.Food;
import com.example.foodapp.database.FavoritesHelper;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment implements SelectListener {

    private FoodAdapter adapter;
    private List<Food> favorites = new ArrayList<>();
    private TextView tv_info;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        tv_info = view.findViewById(R.id.tv_info);

        FavoritesHelper helper = new FavoritesHelper(this.getContext());
        favorites = helper.getAll();

        RecyclerView recyclerView = view.findViewById(R.id.favorites_recyclerView);
        adapter = new FoodAdapter(favorites, this);
        adapter.setFoods(favorites);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if (adapter.getItemCount() == 0)
            tv_info.setText(getResources().getString(R.string.empty_favorites));

        return view;
    }

    @Override
    public void onResume() {
        FavoritesHelper helper = new FavoritesHelper(this.getContext());
        favorites = helper.getAll();
        adapter.setFoods(favorites);
        adapter.notifyDataSetChanged();

        if (adapter.getItemCount() == 0)
            tv_info.setText(getResources().getString(R.string.empty_favorites));

        super.onResume();
    }

    @Override
    public void onItemClicked(Food food) {
        Intent intent = new Intent(this.getContext(), DetailActivity.class);
        intent.putExtra("food", food);
        startActivity(intent);
    }
}