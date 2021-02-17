package com.view.myapplication4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    List<Movie> movies;
    MovieAdapter adapter;

    // 1번 실행
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        adapter = new MovieAdapter(movies);
        gridView.setAdapter(adapter);
    }

    // 2번 실행
    @Override
    protected void onStart() {
        super.onStart();
    }

    // 3번 실행
    @Override
    protected void onResume() {
        super.onResume();
    } // 액티비티에 그림 그려짐

    private void init() {
        gridView = findViewById(R.id.gridView);
        movies = new ArrayList<>();
        download();
    }

    private void download() {
        // 스레드로 돌아야함 2초 걸림

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                movies.add(new Movie(1, "써니", R.drawable.mov01));
                movies.add(new Movie(2, "완득이", R.drawable.mov02));
                movies.add(new Movie(3, "괴물", R.drawable.mov03));
                movies.add(new Movie(4, "라디오스타", R.drawable.mov04));
                movies.add(new Movie(5, "비열한 거리", R.drawable.mov05));
                movies.add(new Movie(6, "왕의 남자", R.drawable.mov06));
                movies.add(new Movie(7, "아일랜드", R.drawable.mov07));
                movies.add(new Movie(8, "웰컴투 동막골", R.drawable.mov08));
                movies.add(new Movie(9, "헬보이", R.drawable.mov09));
                movies.add(new Movie(10, "백투더퓨처", R.drawable.mov10));
                movies.add(new Movie(11, "여인의 향기", R.drawable.mov11));
                movies.add(new Movie(12, "쥬라기 공원", R.drawable.mov12));

                // main 스레드 = ui 스레드가 adapter.notifyDataSetChanged();를 실행시키게 하면 됨.
                // I/O는 언제 얼마나 걸릴지 모르기 때문에 항상 동기화를 해줘야 함.
                runOnUiThread(new Runnable() { // ui 스레드에 던져줌
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }
}