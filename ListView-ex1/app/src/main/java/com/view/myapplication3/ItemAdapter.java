package com.view.myapplication3;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

// 1. 컬렉션 정보
public class ItemAdapter extends BaseAdapter {

    private final List<Movie> movies;
    private static final String TAG = "ItemAdapter";

    public ItemAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    // 전체 크기를 확인하기 위해서 필요 (나도, 어댑터도)
    @Override
    public int getCount() {
        return movies.size();
    }

    // 클릭하거나 어떤 이벤트 발생시에 컬렉션 정보를 확인하기 위해 필요
    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // LayoutInflater inflater1 = LayoutInflater.from(context); // 요즘 방식
    // 객체를 생성해서 그림을 그리는 함수
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.d(TAG, "getView: " + position);
        if(convertView == null) {
            Log.d(TAG, "convertView가 null입니다.");
        } else {
            Log.d(TAG, "convertView가 null이 아닙니다.");
        }

        // 1. 부모 컨텍스트 가져오기 (Context <- MainActivity)
        MainActivity MainActivityContext = (MainActivity)parent.getContext();

        // 2. 인플레이터 객체 생성 완료 (xml을 자바 객체로 만들게 해주는 도구) (메모리에 띄움)
        LayoutInflater inflater = (LayoutInflater) MainActivityContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE); // 옛날방식

        View view = inflater.inflate(R.layout.list_item, parent, false);
        TextView tvTitle = view.findViewById(R.id.tv_title);
        TextView tvSubTitle = view.findViewById(R.id.tv_subtitle);
        tvTitle.setText(movies.get(position).getTitle());
        tvSubTitle.setText(movies.get(position).getSubTitle());

        view.setOnClickListener(v -> {
           // Toast.makeText(context, "클릭됨 " + position, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivityContext, DetailActivity.class);
            intent.putExtra("title", movies.get(position).getTitle());
            MainActivityContext.startActivity(intent);
        });

        view.setOnLongClickListener(v -> {
            Toast.makeText(MainActivityContext, "롱클릭됨" + position, Toast.LENGTH_SHORT).show();
            Log.d(TAG, "movies 사이즈 : " + movies.size());
            movies.remove(position);
            Log.d(TAG, "movies 변경 사이즈 : " + movies.size());
            this.notifyDataSetChanged(); // 데이터 변결 후 UI 동기화시 호출 해야함.
            return true;
        });

        return view;
    }
}
