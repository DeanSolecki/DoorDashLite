package com.deansolecki.doordashlite.viewholders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deansolecki.doordashlite.databinding.ListItemRestaurantBinding;
import com.deansolecki.doordashlite.models.Restaurant;
import com.deansolecki.doordashlite.viewmodels.RestaurantViewModel;

public class RestaurantHolder extends RecyclerView.ViewHolder {
    private ListItemRestaurantBinding mBinding;

    public RestaurantHolder(@NonNull ListItemRestaurantBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
        mBinding.setViewModel(new RestaurantViewModel());
    }

    public void bind(Restaurant restaurant) {
        mBinding.getViewModel().setRestaurant(restaurant);
        mBinding.executePendingBindings();
    }
}
