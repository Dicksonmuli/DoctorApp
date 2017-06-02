package com.dicksonmully6gmail.doctorapp;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

//    member variables to store reference locally
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    // butterknife to make code DRY
    @Bind(R.id.findConditionButton) Button mFindRestaurantsButton;
    @Bind(R.id.conditionEditText) EditText mLocationEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        Typeface alexBrushFont = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Regular.ttf");
        mAppNameTextView.setTypeface(alexBrushFont);



        mFindRestaurantsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
