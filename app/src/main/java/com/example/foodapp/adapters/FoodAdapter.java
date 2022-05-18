package com.example.foodapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.SelectListener;
import com.example.foodapp.data.Food;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    private List<Food> foods;
    private final SelectListener listener;

    public FoodAdapter(List<Food> foods, SelectListener listener) {
        this.foods = foods;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food food = foods.get(position);
        holder.tv_name.setText(food.getName());
        String url = food.getAuxdata().getImg();
        if (!url.isEmpty())
            Picasso.get().load(url).into(holder.iv_image);

        holder.itemView.setOnClickListener(View -> listener.onItemClicked(food));
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_name;
        private final ImageView iv_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_food_name);
            iv_image = itemView.findViewById(R.id.image);
        }
    }
}
