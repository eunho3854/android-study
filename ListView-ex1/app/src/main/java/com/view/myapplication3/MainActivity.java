package com.view.myapplication3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

// stack 생성
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity2";
    private Context mContext = MainActivity.this;
    private ListView lvMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 전체 인플레이트

        // 어댑터 => 내장 어댑터 (간단한 디자인의 리스트)
        // 어댑터 직접 만들어야 되는 경우 (커스텀 리스트 만들 때)
        // 어탭터는 화면 사이즈와 리스트의 하나의 아이템의 사이즈를 알아야하고 그 다음에 데이터 컬렉션을 알아야 한다.

        lvMovie = findViewById(R.id.lv_movie);
        Log.d(TAG, "컨텍스트: " + MainActivity.this);
        Log.d(TAG, "컨텍스트: " + lvMovie.getContext());
        List<Movie> movies = new ArrayList<>();

        for (int i=0; i<20; i++) {

            movies.add(new Movie(i, "제목" + i, "서브제목" + i));
        }

        ItemAdapter adapter = new ItemAdapter(movies);

        lvMovie.setAdapter(adapter);
    }

}