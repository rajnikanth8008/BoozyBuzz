package com.rajnikanth.booze;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class HomePageAdapter extends FragmentPagerAdapter {
    String listOfProducts = "";
    Activity activity;
    FragmentManager supportFragmentManager;

    public HomePageAdapter(@NonNull Activity activity, FragmentManager supportFragmentManager, String listOfProducts) {
        super(supportFragmentManager);
        this.listOfProducts = listOfProducts;
        this.activity = activity;
        this.supportFragmentManager = supportFragmentManager;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new SearchFragment(activity, listOfProducts);
            default:
                return new HomeFragment(activity, supportFragmentManager);
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}

