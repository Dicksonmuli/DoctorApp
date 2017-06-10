package com.dicksonmully6gmail.doctorapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.dicksonmully6gmail.doctorapp.Constants;
import com.dicksonmully6gmail.doctorapp.R;
import com.dicksonmully6gmail.doctorapp.adapters.DoctorListAdapter;
import com.dicksonmully6gmail.doctorapp.models.Doctor;
import com.dicksonmully6gmail.doctorapp.services.BetterDoctorService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by dickson on 6/2/17.
 */

public class DoctorListActivity extends AppCompatActivity {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentAddress;
    public static final String TAG = DoctorListActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private DoctorListAdapter mAdapter;
    //
    public ArrayList<Doctor> mDoctors = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);
//
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
//        mLocationTextView.setText("Here are all the restaurants near: " + location);

        getDoctors(location);

//        testing shared preferences
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, location);
        if (mRecentAddress != null) {
            getDoctors(mRecentAddress);
        };
    }
//    creting options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //            when the query is submitted
            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                getDoctors(query);
                return false;
            }
            //            when there is a noticable change on the search texteditor
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    //callback method for req and res
    private void getDoctors(String location) {
        final BetterDoctorService betterDoctorService = new BetterDoctorService();
        betterDoctorService.findDoctors(location, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            //overriding onResponse and save data in logcat(for now)
            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                calling runOnUiThread() method and override its run()
                    mDoctors = betterDoctorService.processResults(response);
//
//
                DoctorListActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        mAdapter = new DoctorListAdapter(getApplicationContext(), mDoctors);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(DoctorListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                    }

                });


            }
        });
    }
//    adding serached location to shared preferences
    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
    }
}
