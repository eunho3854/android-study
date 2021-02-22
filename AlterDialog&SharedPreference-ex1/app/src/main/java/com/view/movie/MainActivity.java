package com.view.movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Movie;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMovieList;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        List<Integer> movies = download();

        movieAdapter = new MovieAdapter(movies);

        rvMovieList.setAdapter(movieAdapter);
    }

    private void init() {
        rvMovieList = findViewById(R.id.rv_movie_list);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        rvMovieList.setLayoutManager(manager);
    }

    private List<Integer> download() {
        List<Integer> movies = new ArrayList<>();

        movies.add(R.drawable.mov01);
        movies.add(R.drawable.mov02);
        movies.add(R.drawable.mov03);
        movies.add(R.drawable.mov04);
        movies.add(R.drawable.mov05);
        movies.add(R.drawable.mov06);
        movies.add(R.drawable.mov07);
        movies.add(R.drawable.mov08);
        movies.add(R.drawable.mov09);
        movies.add(R.drawable.mov10);
        movies.add(R.drawable.mov11);
        movies.add(R.drawable.mov12);

        return movies;
    }
}