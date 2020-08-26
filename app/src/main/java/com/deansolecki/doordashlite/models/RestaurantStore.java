package com.deansolecki.doordashlite.models;

import java.util.ArrayList;
import java.util.List;

public class RestaurantStore {

    private static RestaurantStore mRestaurantStore;
    private static List<Restaurant> mRestaurants;

    public static List<Restaurant> getRestaurants() {
        if(mRestaurantStore == null) {
            mRestaurantStore = new RestaurantStore();
        }
        return mRestaurants;
    }

    public static void setRestaurants(List<Restaurant> restaurants) {
        if(mRestaurantStore == null) {
            mRestaurantStore = new RestaurantStore();
        }
        mRestaurants.clear();
        mRestaurants.addAll(restaurants);
    }

    private RestaurantStore() {
        mRestaurants = new ArrayList<>();
    }
}
