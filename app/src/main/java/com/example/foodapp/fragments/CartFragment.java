package com.example.foodapp.fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodapp.R;
import com.example.foodapp.activities.OrderPlacedActivity;
import com.example.foodapp.adapters.CartAdapter;
import com.example.foodapp.data.Food;
import com.example.foodapp.database.CartHelper;

import java.util.List;

public class CartFragment extends Fragment implements CartAdapter.itemDeleteListener {

    private List<Food> cartList;
    private CartAdapter adapter;
    private TextView tv_total;
    private CartHelper cartHelper;
    private Resources res;
    private TextView tv_info;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        cartHelper = new CartHelper(this.getContext());
        res = getResources();

        tv_info = view.findViewById(R.id.tv_info_cart);
        if (cartHelper.getCount() == 0) {
            tv_info.setText(res.getString(R.string.empty_cart));
        }

        RecyclerView recyclerView = view.findViewById(R.id.cart_recyclerView);
        cartList = cartHelper.getAll();
        adapter = new CartAdapter(this, cartHelper);
        adapter.setFoods(cartList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        tv_total = view.findViewById(R.id.tv_total);
        Button btn = view.findViewById(R.id.btn_order);

        tv_total.setText(res.getString(R.string.cart_total, cartHelper.getTotal()));

        btn.setOnClickListener(View -> {
            if (cartHelper.getCount() > 0) {
                cartHelper.clear();

                Intent intent = new Intent(this.getContext(), OrderPlacedActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        cartList = cartHelper.getAll();
        adapter.setFoods(cartList);
        adapter.notifyDataSetChanged();
        onItemDelete(cartHelper.getTotal());
    }

    @Override
    public void onItemDelete(double price) {
        if (cartHelper.getCount() == 0) {
            tv_info.setText(res.getString(R.string.empty_cart));
        }
        tv_total.setText(res.getString(R.string.cart_total, price));
    }
}