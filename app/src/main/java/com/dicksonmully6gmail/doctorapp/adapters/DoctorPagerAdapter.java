package com.dicksonmully6gmail.doctorapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dickson on 6/2/17.
 */

public class DoctorPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Doctor> mRestaurants;

    public DoctorPagerAdapter(FragmentManager fm, ArrayList<Doctor> restaurants) {
        super(fm);
        mRestaurants = restaurants;
    }

    @Override
    public Fragment getItem(int position) {
        return DoctorDetailFragment.newInstance(mRestaurants.get(position));
    }

    @Override
    public int getCount() {
        return mRestaurants.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mRestaurants.get(position).getName();
    }
}
