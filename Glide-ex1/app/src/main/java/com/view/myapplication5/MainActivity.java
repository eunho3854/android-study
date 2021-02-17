package com.view.myapplication5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private ImageView iv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv1 = findViewById(R.id.iv_1);

        Glide
                .with(MainActivity.this) // 사진을 어디에 뿌릴건지
                .load("https://picsum.photos/200/300")
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background) // 만약에 사진 없으면 뭘 띄울지
                .into(iv1); // 내부에 runOnUiThread가 있음

    }
}