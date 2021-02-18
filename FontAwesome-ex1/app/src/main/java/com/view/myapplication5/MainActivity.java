package com.view.myapplication5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import info.androidhive.fontawesome.FontDrawable;
import info.androidhive.fontawesome.FontTextView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv1;
    private ImageView ftv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv1 = findViewById(R.id.iv_1);
        ftv = findViewById(R.id.ftv);

        ftv.setOnClickListener(v -> {
            FontDrawable drawable = new FontDrawable(this, R.string.fa_heart_solid, true, false);
            drawable.setTextColor(ContextCompat.getColor(this, android.R.color.darker_gray));
            ftv.setImageDrawable(drawable);
        });

        Glide
                .with(MainActivity.this) // 사진을 어디에 뿌릴건지
                .load("https://picsum.photos/200/300")
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background) // 만약에 사진 없으면 뭘 띄울지
                .into(iv1); // 내부에 runOnUiThread가 있음

    }
}