package com.dicksonmully6gmail.doctorapp.ui;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dicksonmully6gmail.doctorapp.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = CreateAccountActivity.class.getSimpleName();
    FirebaseAuth.AuthStateListener mAuthListener;

    private FirebaseAuth mAuth;
    @Bind(R.id.createUserButton) Button mCreateUserButton;
    @Bind(R.id.nameEditText) EditText mNameEditText;
    @Bind(R.id.emailEditText) EditText mEmailEditText;
    @Bind(R.id.passwordEditText) EditText mPasswordEditText;
    @Bind(R.id.confirmPasswordEditText) EditText mConfirmPasswordEditText;
    @Bind(R.id.loginTextView) TextView mLoginTextView;

    private ProgressDialog mAuthProgressDialog;
    private String mName;

    //overriding onCreate method with onclick listeners
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);
        createAuthStateListener();
        createAuthProgressDialog();

        mAuth = FirebaseAuth.getInstance();
        mLoginTextView.setOnClickListener(this);
        mCreateUserButton.setOnClickListener(this);
    }
    @Override
    public void onStart() {
        super.onStart();
        try {
            mAuth.addAuthStateListener(mAuthListener);
        }catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    //progress dialog method for authenticating with firebase
    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Authenticating with database...");
        mAuthProgressDialog.setCancelable(false);
    }

}
