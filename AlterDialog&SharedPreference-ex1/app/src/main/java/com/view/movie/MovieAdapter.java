package com.view.movie;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// 익명 클래스 내부에서는 외부에 변수(스택)를 접근할 수 는 있는데 변경할 수 없다.
// 전역변수로 만들면 다 해결돼요!!
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private final List<Integer> movies;
    private float saveRating = 0;

    public MovieAdapter(List<Integer> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

         LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         View view = inflater.inflate(R.layout.movie_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setItem(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView movie_image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_image = itemView.findViewById(R.id.movie_image);

            // 어떨 때는 ViewGroup 정보, Activity 정보
            movie_image.setOnClickListener(v -> {

                // 띄울 뷰그룹이 없어서 root가 null
                View dialog = v.inflate(v.getContext(),R.layout.dialog_item, null);
                ImageView ivItem = dialog.findViewById(R.id.movie_image);
                int pos = getAdapterPosition();
                ivItem.setImageResource(movies.get(pos));
                AlertDialog.Builder dlg = new AlertDialog.Builder(v.getContext());

                // 추가

                SharedPreferences pref = v.getContext().getSharedPreferences("pref",MainActivity.MODE_PRIVATE);

                RatingBar ratingBar = dialog.findViewById(R.id.ratingBar);
                TextView tvRating = dialog.findViewById(R.id.rating);
                ratingBar.setRating(pref.getFloat("rating"+pos,0));
                tvRating.setText(pref.getFloat("rating"+pos,0)+"");

                ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> {
                    tvRating.setText(rating+"");
                    saveRating = rating;
                });

                dlg.setTitle("큰 포스터");
                dlg.setIcon(R.drawable.movie_icon);
                dlg.setView(dialog);
                dlg.setNegativeButton("닫기", null);
                dlg.setPositiveButton("확인",(dialogInterface, i) -> {
                    SharedPreferences.Editor ed = pref.edit();
                    ed.putFloat("rating" + pos ,saveRating);
                    ed.commit();
                });
                dlg.show();
            });
        }

        public void setItem(Integer i) {
            movie_image.setImageResource(i);
        }
    }
}
