package com.deansolecki.doordashlite.viewholders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deansolecki.doordashlite.databinding.ListItemRestaurantBinding;

public class RestaurantHolder extends RecyclerView.ViewHolder {
    private ListItemRestaurantBinding mBinding;

    public RestaurantHolder(@NonNull ListItemRestaurantBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }
}
