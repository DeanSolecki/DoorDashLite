package com.deansolecki.doordashlite.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DoorDashService {
    private static DoorDashApi mDoorDashApi;

    public static DoorDashApi getApi() {
        if (mDoorDashApi == null) {
            mDoorDashApi = buildDoorDashApi();
        }
        return mDoorDashApi;
    }

    private static DoorDashApi buildDoorDashApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.doordash.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(DoorDashApi.class);
    }
}
