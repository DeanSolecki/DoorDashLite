package com.deansolecki.doordashlite.api;

import com.deansolecki.doordashlite.models.Restaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DoorDashApi {

    @GET("restaurant/{id}/")
    Call<Restaurant> getRestaurant(@Path("id") int id);

    @GET("restaurants/?lat={lat}&lng={lng}")
    Call<List<Restaurant>> getRestaurants(@Path("lat") float lat, @Path("lng") float lng);

}
