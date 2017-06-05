package com.dicksonmully6gmail.doctorapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dicksonmully6gmail.doctorapp.models.Doctor;
import com.dicksonmully6gmail.doctorapp.ui.DoctorDetailFragment;

import java.util.ArrayList;

/**
 * Created by dickson on 6/2/17.
 */

public class DoctorPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Doctor> mDoctors;

    public DoctorPagerAdapter(FragmentManager fm, ArrayList<Doctor> restaurants) {
        super(fm);
        mDoctors = restaurants;
    }

    @Override
    public Fragment getItem(int position) {
        return DoctorDetailFragment.newInstance(mDoctors.get(position));
    }

    @Override
    public int getCount() {
        return mDoctors.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDoctors.get(position).getName();
    }
}
