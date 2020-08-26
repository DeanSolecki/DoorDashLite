package com.deansolecki.doordashlite;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.deansolecki.doordashlite.api.DoorDashService;
import com.deansolecki.doordashlite.databinding.FragmentFeedBinding;
import com.deansolecki.doordashlite.databinding.ListItemRestaurantBinding;
import com.deansolecki.doordashlite.models.Restaurant;
import com.deansolecki.doordashlite.models.RestaurantStore;
import com.deansolecki.doordashlite.viewholders.RestaurantHolder;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedFragment extends Fragment {
    private static final String TAG = "FeedFragment";

    private List<Restaurant> mRestaurants;

    public static FeedFragment newInstance() {

        Bundle args = new Bundle();

        FeedFragment fragment = new FeedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentFeedBinding binding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_feed, container, false);

        binding.feedRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.feedRecyclerView.setAdapter(new RestaurantAdapter());

        Log.d(TAG, "onCreateView");

        loadRestaurants();

        return binding.getRoot();
    }

    private class RestaurantAdapter extends RecyclerView.Adapter<RestaurantHolder> {

        @NonNull
        @Override
        public RestaurantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            ListItemRestaurantBinding binding = DataBindingUtil
                    .inflate(inflater, R.layout.list_item_restaurant, parent, false);
            return new RestaurantHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull RestaurantHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    private void loadRestaurants() {
        DoorDashService.getApi().getRestaurants(DoorDashService.LAT, DoorDashService.LNG)
//                .enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        Log.d(TAG, response.toString());
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                    }
//                });
                .enqueue(new Callback<List<Restaurant>>() {
                    @Override
                    public void onResponse(Call<List<Restaurant>> call,
                                           Response<List<Restaurant>> response) {
                        if(response.isSuccessful()) {
                            mRestaurants = response.body();
                            RestaurantStore.setRestaurants(mRestaurants);
                        }
                        Log.d(TAG, "Successfully loaded restaurants: " + mRestaurants);
                    }

                    @Override
                    public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                        Log.e(TAG, "Failed to reach API.", t);
                    }
                });
    }
}
