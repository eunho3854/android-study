package com.view.retroex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView rvPostList;
    private PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // PostApi를 new해서 집어 넣은 것
        PostApi postApi = PostApi.retrofit.create(PostApi.class);
        Call<List<Post>> call = postApi.getPosts();
        
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();

                // 어댑터에 던지기 + NotifyChanged
                LinearLayoutManager manager=new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
                rvPostList=findViewById(R.id.rv_post);
                rvPostList.setLayoutManager(manager);

                postAdapter=new PostAdapter(posts);
                rvPostList.setAdapter(postAdapter);

                Log.d(TAG, "onResponse: " + posts);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d(TAG, "onFailure: 실패");
            }
        });
    }
}