package com.example.foodapp.data;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Food> cart = new ArrayList<>();
    private double price = 0;

    public Cart() {

    }

    /**
     * Add a new food item to the cart
     * @param food the food to be added
     */
    public void addItem(Food food) {
        // Make sure that a food was passed
        if (food == null)
            return;

        cart.add(food);
        updatePrice(food.getCost());
    }

    /**
     * Remove a food item from the cart
     * @param food the food to be removed
     */
    public void removeItem (Food food) {
        cart.remove(food);
        // Make the price of the item negativ for removing the cost from the cart
        updatePrice(food.getCost() * -1);
    }

    /**
     * Update the price of the cart
     * @param price the amount to update by
     */
    private void updatePrice(double price) {
        price += price;
    }

    /**
     * Get the size of the cart
     * @return How many item the cart contains
     */
    public int getSize() {
        return cart.size();
    }

    /**
     * Get the price of the cart
     * @return The price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Get the list of items in the cart
     * @return The list
     */
    public List<Food> getCart(){
        return cart;
    }
}
