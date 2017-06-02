package com.dicksonmully6gmail.doctorapp.services;

import android.provider.SyncStateContract;

import com.dicksonmully6gmail.doctorapp.Constants;
import com.dicksonmully6gmail.doctorapp.models.Doctor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dickson on 6/2/17.
 */



    public class BetterDoctorService {
        private static OkHttpClient client =  new OkHttpClient();
        public static void findRestaurants(String location, Callback callback) {
//
//        HttpUrl class to construct the URL we'll send our request to
            HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.API_BASE_URL).newBuilder();
            String url = urlBuilder.build().toString() + location + Constants.BETTER_DOCTOR_APPID + "key=" + Constants.BETTER_DOCTOR_API;

//       create request using the created url
            Request request= new Request.Builder()
                    .url(url)
                    .build();
//        execute this request
            Call call = client.newCall(request);
            call.enqueue(callback);
        }
        //        parse JSON data and return a list of doctors
        public ArrayList<Doctor> processResults(Response response) {
            ArrayList<Doctor> restaurants = new ArrayList<>();

            try {
                String jsonData = response.body().string();
                if (response.isSuccessful()) {
                    JSONObject betterDoctorJSON = new JSONObject(jsonData);
                    JSONArray dataJSON = betterDoctorJSON.getJSONArray("data");
                    for (int i = 0; i < dataJSON.length(); i++) {
                        JSONObject doctorJSON = dataJSON.getJSONObject(i);

                        String first_name = doctorJSON.getJSONObject("profile").getString("first_name");
                        String last_name = doctorJSON.getJSONObject("profile").getString("last_name");
                        String imageUrl = doctorJSON.getJSONObject("profile").getString("image_url");
                        String gender = doctorJSON.getJSONObject("profile").getString("gender");
                        String bio = doctorJSON.getJSONObject("profile").getString("bio");

                        String phone = restaurantJSON.optString("display_phone", "Phone not available");
                        //website
                        JSONArray practicesJSON = doctorJSON.getJSONArray("practices");
                        String website = practicesJSON.getJSONObject(1).getString("website");
                        //lon lat
                        String lat = practicesJSON.getJSONObject(1).getString("lat");
                        String lon = practicesJSON.getJSONObject(1).getString("lon");

                        double rating = restaurantJSON.getDouble("ratings");
                        double latitude = restaurantJSON
                                .getJSONObject("coordinates").getDouble("latitude");
                        double longitude = restaurantJSON
                                .getJSONObject("coordinates").getDouble("longitude");

                        ArrayList<String> specialties = new ArrayList<>();
                        JSONArray specialtyJSON = doctorJSON.getJSONArray("specialties")
                                .getJSONArray(2);
                        for (int y = 0; y < specialtyJSON.length(); y++) {
                            specialties.add(specialtyJSON.toString());
                        }

                        ArrayList<String> specialities = new ArrayList<>();
                        JSONArray categoriesJSON = restaurantJSON.getJSONArray("categories");

                        for (int y = 0; y < categoriesJSON.length(); y++) {
                            categories.add(categoriesJSON.getJSONObject(y).getString("title").toString());
                        }
                        Doctor restaurant = new Doctor(name, phone, website, rating,
                                imageUrl, latitude, longitude, address, categories);
                        restaurants.add(restaurant);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return restaurants;
        }
    }
