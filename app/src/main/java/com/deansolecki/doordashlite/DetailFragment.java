package com.deansolecki.doordashlite;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.deansolecki.doordashlite.databinding.FragmentDetailBinding;
import com.deansolecki.doordashlite.models.Restaurant;
import com.deansolecki.doordashlite.models.RestaurantStore;
import com.deansolecki.doordashlite.viewmodels.RestaurantViewModel;

public class DetailFragment extends Fragment {

    public static final String RESTAURANT_INDEX = "restaurantIndex";

    private int mPosition;

    public static DetailFragment newInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(RESTAURANT_INDEX, position);
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);
        return detailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            mPosition = getArguments().getInt(RESTAURANT_INDEX, -1);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentDetailBinding binding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_detail, container, false);
        RestaurantViewModel restaurantViewModel = new RestaurantViewModel();
        restaurantViewModel.setRestaurant(RestaurantStore.getRestaurants().get(mPosition));
        binding.setViewModel(restaurantViewModel);

        return binding.getRoot();
    }
}
