package com.view.myapplication4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MovieAdapter extends BaseAdapter {

    // final로 만들면 생성자 만들기 편함 ! alt + enter 하면 됨.
    // 굳이 final로 안 해도 됨.
    // Adapter 만들 때 규칙임
    private final List<Movie> movies;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    public void addItem(Movie movie) { // 데이터가 추가될 때
        movies.add(movie);
        notifyDataSetChanged();
    }

    public void removeItem(int position) { // 데이터가 삭제될 때
        movies.remove(position);
        notifyDataSetChanged();
    }
    // 여기까지 규칙


    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MainActivity mainActivityContext = (MainActivity)parent.getContext();

        // 이건 문법임
        LayoutInflater inflater = (LayoutInflater) mainActivityContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.list_item, parent, false);
        TextView tvTitle = view.findViewById(R.id.tv_title);
        ImageView tvImage = view.findViewById(R.id.tv_image);

        tvTitle.setText(movies.get(position).getTitle());
        tvImage.setImageResource(movies.get(position).getPic());

        return view;
    }
}
