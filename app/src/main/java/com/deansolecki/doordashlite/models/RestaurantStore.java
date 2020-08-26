package com.deansolecki.doordashlite.models;

import android.util.Log;

import com.deansolecki.doordashlite.api.DoorDashService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        mRestaurants = restaurants;
    }

    private RestaurantStore() {
        mRestaurants = new ArrayList<>();
    }
}
