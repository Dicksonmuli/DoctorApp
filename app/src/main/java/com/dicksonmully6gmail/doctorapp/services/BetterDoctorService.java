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
        public static void findDoctors(String location, Callback callback) {
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
            ArrayList<Doctor> doctors = new ArrayList<>();

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


                        //website
                        JSONArray practicesJSON = doctorJSON.getJSONArray("practices");
                        String website = practicesJSON.getJSONObject(1).getString("website");
                        //phone
                        String phone = practicesJSON.getJSONObject(1).getJSONArray("phones")
                                .getJSONObject(1)
                                .optString("number", "Phone not available");
                        //lon lat
                        double latitude = practicesJSON.getJSONObject(1).getDouble("lat");
                        double longitude = practicesJSON.getJSONObject(1).getDouble("lon");
                        //address
                        String address = practicesJSON.getJSONObject(2).getString("state_long")
                                + " " +  practicesJSON.getJSONObject(2).getString("street");

                        double rating = doctorJSON.getDouble("ratings");

                        ArrayList<String> specialties = new ArrayList<>();
                        JSONArray specialtyJSON = doctorJSON.getJSONArray("specialties");
                        for (int y = 0; y < specialtyJSON.length(); y++) {
                            specialties.add(specialtyJSON.getJSONObject(y).getString("description").toString());
                        }


                        String name = first_name + " " + last_name;
                        Doctor doctor = new Doctor(name, phone, website, rating,
                                imageUrl, latitude, longitude, address, specialties, gender, bio);
                        doctors.add(doctor);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return doctors;
        }
    }
