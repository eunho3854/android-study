package com.view.movieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.view.movieapp.adapter.MovieAdapter;
import com.view.movieapp.model.Movie;
import com.view.movieapp.service.MovieService;
import com.view.movieapp.viewModel.MovieViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView rvMovie;
    private MovieAdapter movieAdapter;
    private MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initData();
        initObserve();
        initListener();
    }

    private void init() {
        rvMovie = findViewById(R.id.rv_movie);

        // 다른 곳에서 쓸일이 없어서 전역으로 안 함.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvMovie.setLayoutManager(layoutManager);

        movieAdapter = new MovieAdapter(MainActivity.this);
        rvMovie.setAdapter(movieAdapter);

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
    }

    private void initData() {
        movieViewModel.findAll();
    }

    private void initObserve() {
        movieViewModel.subscribe().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                movieAdapter.setMovies(movies);
            }
        });
    }

    private  void initListener() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,  ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {


            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Log.d(TAG, "onSwiped: "+viewHolder.getAdapterPosition());
                long id = movieAdapter.getMovieId(viewHolder.getAdapterPosition());
                movieViewModel.deleteById(id);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rvMovie);
    }
}