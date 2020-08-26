package com.deansolecki.doordashlite.viewmodels;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.deansolecki.doordashlite.models.Restaurant;
import com.squareup.picasso.Picasso;

public class RestaurantViewModel extends BaseObservable {
    private Restaurant mRestaurant;
    private View.OnClickListener mOnClickListener;


    @Bindable
    public Restaurant getRestaurant() {
        return mRestaurant;
    }

    @Bindable
    public View.OnClickListener getOnClickListener() {
        return mOnClickListener;
    }

    public void setRestaurant(Restaurant restaurant) {
        mRestaurant = restaurant;
        notifyChange();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        if(url == null || url.length() == 0 || view == null) {
            return;
        }
        Picasso.get()
                .load(url)
                .resize(200, 200)
                .centerInside()
                .into(view);
    }

    @BindingAdapter("imageUrlLarge")
    public static void loadImageLarge(ImageView view, String url) {
        if(url == null || url.length() == 0 || view == null) {
            return;
        }
        Picasso.get()
                .load(url)
                .into(view);
    }
}
