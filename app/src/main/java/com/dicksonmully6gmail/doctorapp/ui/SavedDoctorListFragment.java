package com.dicksonmully6gmail.doctorapp.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dicksonmully6gmail.doctorapp.Constants;
import com.dicksonmully6gmail.doctorapp.R;
import com.dicksonmully6gmail.doctorapp.adapters.FirebaseDoctorViewHolder;
import com.dicksonmully6gmail.doctorapp.models.Doctor;
import com.dicksonmully6gmail.doctorapp.util.OnStartDragListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedDoctorListFragment extends Fragment implements OnStartDragListener {

    private FirebaseDoctorListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    public SavedDoctorListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved_doctor_list, container, false);
        ButterKnife.bind(this, view);
        setUpFirebaseAdapter();
        return view;
    }
    private void setUpFirebaseAdapter() {
        /** getting current user by user id
         * in order to display "Saved Restaurants" list
         * associated with the user currently logged in
         */
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        /* Query object
         * Refactoring - creating query object in place of DatabaseReference
         * FirebaseArrayAdapter accepts either a DatabaseReference or a Query
         */
        Query query = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_RESTAURANTS)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);

        /** passing in query in place of DatabaseReference
         *  In line below, we change 6th parameter 'this' to 'getActivity()'
         *  because fragments do not have own context:
         */
        mFirebaseAdapter = new FirebaseDoctorListAdapter
                (Doctor.class, R.layout.doctor_list_item_drag, FirebaseDoctorViewHolder.class,
                        query, this, getActivity());

        mRecyclerView.setHasFixedSize(true);
        //In line below, we change  parameter 'this' to 'getActivity()'
        //because fragments do not have own context
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mFirebaseAdapter);
        //adding AdapterDataObserver on firebase adapter to help load saved restaurants
        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                mFirebaseAdapter.notifyDataSetChanged();
            }
        });


//        attach itemTouchHelper to enable the interfaces to communicate with the necessary callbacks
        ItemTouchHelper.Callback callback = new com.dicksonmully6gmail.doctorapp.util.SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    //method is now public
    public void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    /** onstartdrag listener override
     *  which will eventually send our touch events back to our SimpleItemTouchHelperCallback
     * @param viewHolder
     */
    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

}