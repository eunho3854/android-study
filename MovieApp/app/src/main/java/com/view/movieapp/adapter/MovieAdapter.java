package com.view.movieapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.view.movieapp.MainActivity;
import com.view.movieapp.R;
import com.view.movieapp.databinding.MovieItemBinding;
import com.view.movieapp.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private static final String TAG = "MovieAdapter";
    private List<Movie> movies = new ArrayList<>();

    private final MainActivity mContext;

    public MovieAdapter(MainActivity mContext) {
        this.mContext = mContext;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public long getMovieId(int position) {
        return movies.get(position).getId();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieItemBinding movieItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.movie_item, parent, false);
        return new MovieViewHolder(movieItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.movieItemBinding.setMovie(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    // movie_item 디자인이 연결.
    class MovieViewHolder extends RecyclerView.ViewHolder {

        /* 데이터 바인딩 순서
           1. 앱 수준의 Gradle 가서
           dataBinding { enabled = true } 작성

           2. movie_item을 layout으로 감싸고 name, type 설정

           3. Build -> Make Project
        */

        // card_item 디자인이 연결
        private MovieItemBinding movieItemBinding;

        public MovieViewHolder(@NonNull MovieItemBinding movieItemBinding) {
            super(movieItemBinding.getRoot());
            this.movieItemBinding = movieItemBinding;
        }
    }
}
