package com.view.myapplication4;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;
import com.view.myapplication4.helper.NavigationViewHelper;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbarMain;
    private ImageView ivMenu, ivPerson;
    private DrawerLayout drawer;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 원래 툴바에서 새로 만든 툴바로 바꾸기
        toolbarMain = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbarMain);

        ivPerson = findViewById(R.id.iv_person);
        ivPerson.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PersonActivity.class);
            startActivity(intent);
        });

        ivMenu = findViewById(R.id.iv_menu);
        drawer = findViewById(R.id.drawer);
        ivMenu.setOnClickListener(v -> {
            drawer.openDrawer(GravityCompat.START);
        });

        nv = findViewById(R.id.nv);

        // 다른 액티비티에도 재사용이 가능함.
        NavigationViewHelper.enable(MainActivity.this, nv);
    }
}