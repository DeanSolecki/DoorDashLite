package com.deansolecki.doordashlite.models;

import java.util.List;

public class Restaurant{
    public boolean is_time_surging;
    public int max_order_size;
    public int delivery_fee;
    public int max_composite_score;
    public int id;
    public List<MerchantPromotion> merchant_promotions;
    public double average_rating;
    public List<Menu> menus;
    public int composite_score;
    public String status_type;
    public boolean is_only_catering;
    public String status;
    public int number_of_ratings;
    public int asap_time;
    public String description;
    public Business business;
    public List<String> tags;
    public int yelp_review_count;
    public int business_id;
    public int extra_sos_delivery_fee;
    public double yelp_rating;
    public String cover_img_url;
    public String header_img_url;
    public Address address;
    public int price_range;
    public String slug;
    public String name;
    public boolean is_newly_added;
    public String url;
    public double service_rate;
    public Object promotion;
    public Object featured_category_description;
    public boolean is_favorite;
}
