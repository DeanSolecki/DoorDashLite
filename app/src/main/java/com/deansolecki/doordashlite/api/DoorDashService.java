package com.deansolecki.doordashlite.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DoorDashService {
    public static final String LAT = "37.422740";
    public static final String LNG = "-122.139956";
    private static DoorDashApi mDoorDashApi;
    private static OkHttpClient mOkHttpClient;

    public static DoorDashApi getApi() {
        if (mDoorDashApi == null) {
            mOkHttpClient = new OkHttpClient();
            mDoorDashApi = buildDoorDashApi();
        }
        return mDoorDashApi;
    }

    public static OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    private static DoorDashApi buildDoorDashApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.doordash.com/v2/")
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(DoorDashApi.class);
    }
}
