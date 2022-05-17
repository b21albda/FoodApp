package com.example.foodapp.fragments;

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
import com.example.foodapp.adapters.CartAdapter;
import com.example.foodapp.data.Food;
import com.example.foodapp.database.CartHelper;

import java.util.List;

public class CartFragment extends Fragment implements CartAdapter.itemDeleteListener {

    private List<Food> cartList;
    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private TextView tv_total;
    private CartHelper cartHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        cartHelper = new CartHelper(this.getContext());

        if (cartHelper.getCount() == 0) {
            TextView tv_info = view.findViewById(R.id.tv_info_cart);
            tv_info.setText("Cart Empty");
        }

        recyclerView = view.findViewById(R.id.cart_recyclerView);
        cartList = cartHelper.getAll();
        adapter = new CartAdapter(this, cartHelper);
        adapter.setFoods(cartList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        tv_total = view.findViewById(R.id.tv_total);
        Button btn = view.findViewById(R.id.btn_order);

        tv_total.setText("Total: " + cartHelper.getTotal() + " Kr");

        btn.setOnClickListener(View -> {
            // Todo Clear cart and display order placed
        });

        return view;
    }

    @Override
    public void onItemDelete(double price) {
        tv_total.setText("Total: " + price + " Kr");
    }
}