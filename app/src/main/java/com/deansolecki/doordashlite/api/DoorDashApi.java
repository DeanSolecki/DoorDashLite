package com.deansolecki.doordashlite.api;

import com.deansolecki.doordashlite.models.Restaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DoorDashApi {

    @GET("restaurant/{id}/")
    Call<Restaurant> getRestaurant(@Path("id") int id);

    @GET("restaurant/")
    Call<List<Restaurant>> getRestaurants(@Query("lat") String lat, @Query("lng") String lng);

}
