package com.dicksonmully6gmail.doctorapp.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dicksonmully6gmail.doctorapp.R;
import com.dicksonmully6gmail.doctorapp.models.Doctor;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.R.attr.src;

/**
 * Created by dickson on 6/2/17.
 */

public class DoctorDetailFragment extends Fragment implements View.OnClickListener {
    private static final int MAX_WIDTH =300;
    private static final int MAX_HEIGHT = 300;
    @Bind(R.id.doctorImageView) ImageView mImageLabel;
    @Bind(R.id.doctorNameTextView) TextView mNameLabel;
    @Bind(R.id.specialtyTextView) TextView mSpecilityLabel;
    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.saveRestaurantButton) TextView mSaveDoctorButton;

    private Doctor mDoctor;

    public static DoctorDetailFragment newInstance(Doctor doctor) {
        //wrapping doctor with parcels for serialization
        DoctorDetailFragment restaurantDetailFragment = new DoctorDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("doctor", Parcels.wrap(doctor));
        restaurantDetailFragment.setArguments(args);
        return restaurantDetailFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
//        unwrapping restaurant on onCreate
        super.onCreate(savedInstanceState);
        mDoctor = Parcels.unwrap(getArguments().getParcelable("doctor"));
    }

    //    restaurant object used to set our ImageView and TextViews in onCreateView on onCreate view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflating the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doctor_detail, container, false);
        ButterKnife.bind(this, view);


        Picasso.with(view.getContext())
                .load(mDoctor.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mImageLabel);


        mNameLabel.setText(mDoctor.getName());
        mSpecilityLabel.setText(android.text.TextUtils.join(", ", mDoctor.getSpecialties()));
        mRatingLabel.setText(Double.toString(mDoctor.getRating()) + "/5");
        mPhoneLabel.setText(mDoctor.getPhone());
        mAddressLabel.setText(mDoctor.getAddress());

        mWebsiteLabel.setOnClickListener( this);
        mPhoneLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener( this);
        return view;
    }
    //    implicit intent
    @Override
    public void onClick(View v) {
        if (v == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mDoctor.getWebsite()));
            startActivity(webIntent);
        }
        if (v == mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + mDoctor.getPhone()));
            startActivity(phoneIntent);
        }
        if (v == mAddressLabel) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:" + mDoctor.getLatitude()
                            + "," + mDoctor.getLongitude()
                            + "?q=(" + mDoctor.getName() + ")"));
            startActivity(mapIntent);
        }
    }

}
