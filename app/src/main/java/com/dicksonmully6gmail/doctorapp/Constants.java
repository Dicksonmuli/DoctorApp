package com.dicksonmully6gmail.doctorapp;

/**
 * Created by dickson on 6/2/17.
 */

public class Constants {
    public static final String BETTER_DOCTOR_API = BuildConfig.BETTER_DOCTOR_API;
    public static final String API_BASE_URL = "https://api.betterdoctor.com/2016-03-01/doctors?location=";
    public static final String BETTER_DOCTOR_APPID = ",500&skip=0&limit=100&user_key=";
    public static final String PREFERENCES_LOCATION_KEY = "location";
    public static final String FIREBASE_CHILD_SEARCHED_LOCATION = "searchedLocation";
    public static final String FIREBASE_CHILD_DOCTORS = "doctors";
    //        index string constant to reference the 'index' key of Restaurant objects
    public static final String FIREBASE_QUERY_INDEX = "index";

    public static final String EXTRA_KEY_POSITION = "position";
    public static final String EXTRA_KEY_DOCTORS = "doctors";
    // navigation constants
    public static final String KEY_SOURCE = "source";
    public static final String SOURCE_SAVED ="saved";
    public static final String SOURCE_FIND = "find";
}
