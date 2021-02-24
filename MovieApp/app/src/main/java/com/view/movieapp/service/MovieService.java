package com.view.movieapp.service;

import com.view.movieapp.model.Movie;
import com.view.movieapp.model.ResponseDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieService {

    @GET("api/movie")
    Call<ResponseDto<List<Movie>>> findAll();
    // Call에 담아야지 에러를 구분 가능.

    @DELETE("api/movie/{id}")
    Call<ResponseDto> deleteById(@Path("id") long id);

    // http:// + 내 ip 주소 + : 8000
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.0.2:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
