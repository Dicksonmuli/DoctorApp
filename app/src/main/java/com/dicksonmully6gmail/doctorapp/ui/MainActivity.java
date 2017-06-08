package com.dicksonmully6gmail.doctorapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dicksonmully6gmail.doctorapp.Constants;
import com.dicksonmully6gmail.doctorapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

//    member variables to store reference locally
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    // butterknife to make code DRY
    @Bind(R.id.findDoctorButton) Button mFindDoctorButton;
    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.savedDoctorsButton) Button mSavedDoctorsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        Typeface openSansRegular = Typeface.createFromAsset(getAssets(), "fonts/Black_Diamonds.ttf");
        mAppNameTextView.setTypeface(openSansRegular);



        mFindDoctorButton.setOnClickListener(this);
        mSavedDoctorsButton.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
//        firebase authStateListener
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                //Welcome message
                if (user != null) {
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName());
                }else {

                }
            }
        };
    }
    //overriding onstart
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    //overriding onStop
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onClick(View v) {
        if(v == mFindDoctorButton) {
                String location = mLocationEditText.getText().toString();
//                if(!(location).equals("")) {
//                    addToSharedPreferences(location);
//                }
                Intent intent = new Intent(MainActivity.this, DoctorListActivity.class);
                intent.putExtra("location", location);
                startActivity(intent);


        }
        //saved doctor button onclick listener
        if (v == mSavedDoctorsButton) {
            Intent intent = new Intent(MainActivity.this, SavedDoctorListActivity.class);
            startActivity(intent);
        }
    }
    //a method  which takes the user-inputted zip code
//    private void addToSharedPreferences(String location) {
//        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
//    }
}
