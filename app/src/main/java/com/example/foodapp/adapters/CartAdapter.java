package com.example.foodapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.data.Food;
import com.example.foodapp.database.CartHelper;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<Food> foods;
    private final itemDeleteListener listener;
    private final CartHelper cartHelper;

    public CartAdapter(itemDeleteListener listener, CartHelper cartHelper) {
        this.listener = listener;
        this.cartHelper = cartHelper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food food = foods.get(position);
        holder.tv_name.setText(food.getName());
        holder.tv_price.setText(food.getCost() + " Kr");
        String url = food.getAuxdata().getImg();
        if (!url.isEmpty())
            Picasso.get().load(url).into(holder.iv_product);

        holder.btn_delete.setOnClickListener(view -> {
            foods.remove(food);
            cartHelper.delete(String.valueOf(food.getDbId()));

            listener.onItemDelete(cartHelper.getTotal());
            this.notifyDataSetChanged();
        });
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
        private final TextView tv_price;
        private final ImageView iv_product;
        private final ImageButton btn_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name_cart);
            tv_price = itemView.findViewById(R.id.tv_price_cart);
            iv_product = itemView.findViewById(R.id.iv_cart);
            btn_delete = itemView.findViewById(R.id.btn_remove_from_cart);
        }
    }

    public interface itemDeleteListener{
        void onItemDelete(double price);
    }
}
