package com.view.movieapp.model;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;
import com.makeramen.roundedimageview.RoundedImageView;
import com.view.movieapp.R;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private long id;
    private String url;
    private String title;
    private long year;
    private double rating;
    private long runtime;
    private String summary;

    // 커멜 표기법 사용법
    @SerializedName("medium_cover_image")
    private String mediumCoverImage;

    // Glide 사용
    @BindingAdapter({"bind:mediumCoverImage"})
    public static void loadImage(RoundedImageView view, String mediumCoverImage){

        Glide.with(view.getContext())
                .load(mediumCoverImage)
                .placeholder(R.drawable.main)
                .into(view);
    }
}

