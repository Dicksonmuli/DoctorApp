package com.dicksonmully6gmail.doctorapp.ui;

import android.os.Bundle;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dicksonmully6gmail.doctorapp.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dickson on 6/9/17.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.passwordLoginButton) Bundle mPasswordLoginButton;
    @Bind(R.id.emailEditText) EditText mEmailEditText;
    @Bind(R.id.passwordEditText) EditText mPasswordEditText;
    @Bind(R.id.registerTextView) TextView mRegisterTextView;

    private FirebaseAuth mAuth;
    public static final String TAG = LoginActivity.class.getSimpleName();
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog mAuthProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        mRegisterTextView.setOnClickListener(this);
        createAuthProgressDialog();
    }
    @Override
    public void onClick(View v) {

    }


}
