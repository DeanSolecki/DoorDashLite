package com.deansolecki.doordashlite.models;

import android.content.Context;
import android.content.SharedPreferences;

import com.deansolecki.doordashlite.MyApplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RestaurantStore {

    private static final String PACKAGE_NAME = "com.deansolecki.doordashlite";
    private static final String FAVORITES_SET = "favoritesSet";

    private static RestaurantStore mRestaurantStore;
    private static List<Restaurant> mRestaurants;
    private static List<String> mFavorites;

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
        labelFavorites();
    }

    private RestaurantStore() {
        mRestaurants = new ArrayList<>();
    }

    private static void labelFavorites() {
        if(mFavorites == null) {
            retrieveFavorites();
        }
        for(String id : mFavorites) {
            for(Restaurant restaurant : mRestaurants) {
                if(Integer.parseInt(id) == restaurant.id) {
                    restaurant.is_favorite = true;
                }
            }
        }
    }

    private static void retrieveFavorites() {
        SharedPreferences sharedPref = MyApplication
                .getContext().getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE);
        Set<String> favoritesSet = sharedPref.getStringSet(FAVORITES_SET, null);
        if(mFavorites == null) {
            mFavorites = new ArrayList<>();
        } else {
            mFavorites.clear();
        }
        if(favoritesSet != null) {
            mFavorites.addAll(favoritesSet);
        }
    }

    public static void storeFavorite(int id, boolean isFavorite) {
        for(Restaurant restaurant : mRestaurants) {
            if(restaurant.id == id) {
                restaurant.is_favorite = isFavorite;
                boolean isFound = false;
                for(String favorite : mFavorites) {
                    if(Integer.toString(restaurant.id).equals(favorite)) {
                        if(!isFavorite) {
                            mFavorites.remove(Integer.toString(id));
                        } else {
                            isFound = true;
                        }
                        break;
                    }
                }
                if(!isFound) {
                    if(isFavorite) {
                        mFavorites.add(Integer.toString(id));
                    }
                }
            }
        }
        SharedPreferences sharedPref = MyApplication.getContext()
                .getSharedPreferences(PACKAGE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Set<String> favoriteSet = new HashSet<>();
        favoriteSet.addAll(mFavorites);
        editor.putStringSet(FAVORITES_SET, favoriteSet);
        editor.commit();
    }
}
