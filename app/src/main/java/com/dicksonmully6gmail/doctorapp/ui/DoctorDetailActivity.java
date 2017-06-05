package com.dicksonmully6gmail.doctorapp.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.dicksonmully6gmail.doctorapp.R;
import com.dicksonmully6gmail.doctorapp.adapters.DoctorPagerAdapter;
import com.dicksonmully6gmail.doctorapp.models.Doctor;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dickson on 6/2/17.
 */

public class DoctorDetailActivity extends AppCompatActivity{
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private DoctorPagerAdapter adapterViewPager;
    ArrayList<Doctor> mDoctors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);
        ButterKnife.bind(this);

//        pull out our ArrayList<Doctor> Parcelable using the unwrap() method
        mDoctors = Parcels.unwrap(getIntent().getParcelableExtra("doctors"));
        int startingPosition = getIntent().getIntExtra("position", 0);

//        instructing ViewPager to use adapterViewPager adapter. And set the current item to the position of the item that was just clicked on
        adapterViewPager = new DoctorPagerAdapter(getSupportFragmentManager(), mDoctors);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
