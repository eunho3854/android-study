package com.view.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SubActivity extends AppCompatActivity {

    private FloatingActionButton fabPop;
    private static final String TAG = "SubActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();

        // Bundle
        Bundle bundle = intent.getBundleExtra("userBundle");
        Log.d(TAG, "userBundle: " + bundle.getSerializable("user"));

        // Serializable
        Log.d(TAG, "User : " + intent.getSerializableExtra("user"));

        fabPop = findViewById(R.id.fab_pop);
        fabPop.setOnClickListener(v -> {
            // 인증이 성공함
            // 데이터 던지기
            Intent newIntent = new Intent();
            newIntent.putExtra("auth", "ok");
            setResult(1, newIntent);
            finish(); // pop
        });
    }
}