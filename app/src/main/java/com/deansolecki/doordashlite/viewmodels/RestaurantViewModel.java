package com.deansolecki.doordashlite.viewmodels;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.deansolecki.doordashlite.models.Restaurant;
import com.squareup.picasso.Picasso;

public class RestaurantViewModel extends BaseObservable {
    private Restaurant mRestaurant;

    @Bindable
    public Restaurant getRestaurant() {
        return mRestaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        mRestaurant = restaurant;
        notifyChange();
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        if(url == null || url.length() == 0) {
            return;
        }
        Picasso.get()
                .load(url)
                .resize(200, 200)
                .centerInside()
                .into(view);
    }

    public void onButtonClicked() {

    }
}
