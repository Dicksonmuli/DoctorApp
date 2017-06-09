package com.dicksonmully6gmail.doctorapp.models;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dickson on 6/2/17.
 */

@Parcel
public class Doctor {
    // fields must be public
    String name;
    String phone;
    String website;
    double rating;
    String imageUrl;
    String address;
    double latitude;
    double longitude;
    List<String> specialties = new ArrayList<>();
    String gender;
    String bio;
    private  String pushId;

    //    empty constructor needed by the Parceler library
    public Doctor() {
    }

    public Doctor(String name, String phone, String website,
                  double rating, String imageUrl, double latitude, double longitude, String address,
                  ArrayList<String> specialties, String gender, String bio) {
        this.name = name;
        this.phone = phone;
        this.website = website;
        this.rating = rating;
        this.imageUrl = imageUrl;
//        mImageUrl = getLargeImageUrl(imageUrl);
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.specialties = specialties;
        this.gender = gender;
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public double getRating() {
        return rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    //refactoring image getter method to retrieve high quality image
    public String getLargeImageUrl(String imageUrl) {
        String largeImageUrl = imageUrl.substring(0, imageUrl.length() - 5).concat("o.jpg");
        return largeImageUrl;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public List<String> getSpecialties() {
        return specialties;
    }

    public String getGender() {
        return gender;
    }

    public String getBio() {
        return bio;
    }

    public String getPushId() {
        return pushId;
    }
    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}


