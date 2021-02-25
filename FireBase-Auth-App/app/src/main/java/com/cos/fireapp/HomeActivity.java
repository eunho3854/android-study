package com.cos.fireapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    private Button btnLogout;
    private FirebaseAuth mAuth;
    private TextView tvUserEmail, tvUserUid;
    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        Log.d(TAG, "onCreate: 로그인 사용자: " + user.getEmail());

        btnLogout = findViewById(R.id.btn_logout);
        tvUserEmail = findViewById(R.id.tv_userEmail);
        tvUserUid = findViewById(R.id.tv_userUid);


        if (user != null) {
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            boolean emailVerified = user.isEmailVerified();

            String uid = user.getUid();

            tvUserEmail.setText(email);
            tvUserUid.setText(uid);
        }


        btnLogout.setOnClickListener(v -> {

            // 로그아웃
            mAuth.signOut();
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        });
    }
}