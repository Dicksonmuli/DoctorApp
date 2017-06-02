package com.dicksonmully6gmail.doctorapp.models;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by dickson on 6/2/17.
 */

@Parcel
public class Doctor {
    // fields must be public
    String mName;
    String mPhone;
    String mWebsite;
    double mRating;
    String mImageUrl;
    ArrayList<String> mAddress = new ArrayList<>();
    double mLatitude;
    double mLongitude;
    ArrayList<String> mSpecialities = new ArrayList<>();

    //    empty constructor needed by the Parceler library
    public Doctor() {
    }

    public Doctor(String name, String phone, String website,
                  double rating, String imageUrl, double latitude, double longitude, ArrayList<String> address,
                  ArrayList<String> specialities) {
        this.mName = name;
        this.mPhone = phone;
        this.mWebsite = website;
        this.mRating = rating;
        this.mImageUrl = imageUrl;
//        mImageUrl = getLargeImageUrl(imageUrl);
        this.mAddress = address;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
        this.mSpecialities = specialities;
    }

    public String getName() {
        return mName;
    }

    public String getPhone() {
        return mPhone;
    }

    public String getWebsite() {
        return mWebsite;
    }

    public double getRating() {
        return mRating;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    //refactoring image getter method to retrieve high quality image
    public String getLargeImageUrl(String imageUrl) {
        String largeImageUrl = imageUrl.substring(0, imageUrl.length() - 5).concat("o.jpg");
        return largeImageUrl;
    }

    public ArrayList<String> getAddress() {
        return mAddress;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public ArrayList<String> getSpecialities() {
        return mSpecialities;
    }
}


