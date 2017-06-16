package com.dicksonmully6gmail.doctorapp.util;

import com.dicksonmully6gmail.doctorapp.models.Doctor;

import java.util.ArrayList;

/**
 * Created by dickson on 6/16/17.
 */

public interface OnDoctorSelectedListener {
    /**
     * @param position
     * @param doctors
     * @param source -source will be the String name of the activity the user views our reusable fragment,
     *               from; Either "RestaurantListActivity" or "SavedRestaurantListActivity"
     */
    public void onRestaurantSelected(Integer position,
                                     ArrayList<Doctor> doctors, String source);

}
