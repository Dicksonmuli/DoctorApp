package com.dicksonmully6gmail.doctorapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dicksonmully6gmail.doctorapp.Constants;
import com.dicksonmully6gmail.doctorapp.R;
import com.dicksonmully6gmail.doctorapp.models.Doctor;
import com.dicksonmully6gmail.doctorapp.ui.DoctorDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by dickson on 6/8/17.
 */

public class FirebaseDoctorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private static final int  MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;

    public FirebaseDoctorViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }
//    bind doctor views
    public void bindDoctor(Doctor doctor) {
        ImageView doctorImageView = (ImageView) mView.findViewById(R.id.doctorImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.doctorNameTextView);
        TextView specialtyTextView = (TextView) mView.findViewById(R.id.specialtyTextView);
        TextView ratingTextView = (TextView) mView.findViewById(R.id.ratingTextView);

        Picasso.with(mContext)
                .load(doctor.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(doctorImageView);

        try {
            nameTextView.setText(doctor.getName());
        }catch (NullPointerException e) {
            e.printStackTrace();
        }
        specialtyTextView.setText(doctor.getSpecialties().get(0));
        ratingTextView.setText("Rating: " + doctor.getRating() + "/5");

    }
//    adding value event listener on onclick
    @Override
    public void onClick(View v) {
        final ArrayList<Doctor> doctors = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_DOCTORS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    doctors.add(snapshot.getValue(Doctor.class));
                }
                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, DoctorDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("doctors", Parcels.wrap(doctors));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
