package com.deansolecki.doordashlite.viewmodels;

import android.view.View;

import com.deansolecki.doordashlite.models.Restaurant;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class RestaurantViewModelTest {
    private Restaurant mRestaurant;
    private View.OnClickListener mOnClickListener;
    private RestaurantViewModel mSubject;

    @Before
    public void setUp() {
        mOnClickListener = mock(View.OnClickListener.class);
        mRestaurant = mock(Restaurant.class);
        mSubject = new RestaurantViewModel();
        mSubject.setRestaurant(mRestaurant);
        mSubject.setOnClickListener(mOnClickListener);
    }

    @Test
    public void getRestaurant() {
        assertThat(mRestaurant.name, is(mSubject.getRestaurant().name));
    }

    @Test
    public void getOnClickListener() {
        assertThat(mOnClickListener, is(mSubject.getOnClickListener()));
    }

    @Test
    public void loadImage() {
        // Should not fail on null values.
        RestaurantViewModel.loadImage(null, null);
    }

    @Test
    public void loadImageLarge() {
        // Should not fail on null values.
        RestaurantViewModel.loadImageLarge(null, null);
    }
}