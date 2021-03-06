package com.deansolecki.doordashlite;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.deansolecki.doordashlite.api.DoorDashService;
import com.deansolecki.doordashlite.databinding.FragmentFeedBinding;
import com.deansolecki.doordashlite.databinding.ListItemRestaurantBinding;
import com.deansolecki.doordashlite.models.Restaurant;
import com.deansolecki.doordashlite.models.RestaurantStore;
import com.deansolecki.doordashlite.viewholders.RestaurantHolder;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("NullableProblems")
public class FeedFragment extends Fragment {
    private static final String TAG = "FeedFragment";

    private List<Restaurant> mRestaurants;
    private RestaurantAdapter mRestaurantAdapter;
    private ContentLoadingProgressBar mProgressBar;

    public static FeedFragment newInstance() {

        Bundle args = new Bundle();

        FeedFragment fragment = new FeedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(mRestaurants == null) {
            mRestaurants = RestaurantStore.getRestaurants();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentFeedBinding binding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_feed, container, false);

        binding.feedRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRestaurantAdapter = new RestaurantAdapter(mRestaurants);
        binding.feedRecyclerView.setAdapter(mRestaurantAdapter);

        mProgressBar = binding.progressBar;

        loadRestaurants();

        return binding.getRoot();
    }

    private class RestaurantAdapter extends RecyclerView.Adapter<RestaurantHolder> {
        private final List<Restaurant> mRestaurants;

        public RestaurantAdapter(List<Restaurant> restaurants) {
            mRestaurants = restaurants;
        }

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
            Restaurant restaurant = mRestaurants.get(position);
            holder.itemView.setOnClickListener(view -> launchDetailFragment(position));
            holder.bind(restaurant);
        }

        @Override
        public int getItemCount() {
            return mRestaurants.size();
        }
    }

    private void loadRestaurants() {
        if(mRestaurants.size() > 0) {
            mProgressBar.hide();
            return;
        }
        DoorDashService.getApi().getRestaurants(DoorDashService.LAT, DoorDashService.LNG)
                .enqueue(new Callback<List<Restaurant>>() {
                    @Override
                    public void onResponse(Call<List<Restaurant>> call,
                                           Response<List<Restaurant>> response) {
                        if(response.isSuccessful()) {
                            mRestaurants = response.body();
                            RestaurantStore.setRestaurants(mRestaurants);
                            mRestaurantAdapter.notifyDataSetChanged();
                            mProgressBar.hide();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                        Log.e(TAG, "Failed to reach API.", t);
                    }
                });
    }

    private void launchDetailFragment(int position) {
        DetailFragment detailFragment = DetailFragment.newInstance(position);
        FragmentManager fragmentManager =
                Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, detailFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
