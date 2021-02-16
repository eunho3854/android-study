package com.view.myapplication3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

// stack 생성
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity2";
    private Context mContext = MainActivity.this; // Context = 모든 정보
    private FloatingActionButton fabRoute;
    private ConstraintLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.main_Layout);

        fabRoute = findViewById(R.id.fab_route);
        fabRoute.setOnClickListener(v -> {
            // 1. 현재 내 화면, 이동할 화면
            // 2. 현재 내 화면, 내부 앱의 이동할 화면
            // Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01022227777"));
            // Intent = 트럭 ( 현재 내 위치, 이동할 위치정보, 이동할 때 가져갈 박스 )
            // 다른 앱으로 이동 = 트럭 ( 상대방 맵의 키, 데이터 )

            User user = new User();
            user.setId(1);
            user.setUsername("cos");
            user.setPassword("1234");

            // serializable
            Intent intent = new Intent(mContext, SubActivity.class);
            intent.putExtra("user", user);

            // Bundle (택배박스)
            Bundle bundle = new Bundle();
            bundle.putSerializable("user", user);
            intent.putExtra("userBundle", bundle);

            // startActivity(intent);

            // 내가 보낸 게 맞나 확인하기 위해 리퀘스트 코드를 보냄
            startActivityForResult(intent, 300);
            
        });
    }

    // finish() 될 때 콜백되는 함수
    // setResult() 함수의 파라메터가 전달됨.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "onActivityResult: 실햄됨");
        Log.d(TAG, "requestCode: " + requestCode);
        Log.d(TAG, "resultCode: " + resultCode);

        // Window가 무엇인지? AlertDialog 사용!!

        if (requestCode == 300) { // 서브액티비티에서 돌아왔다.
            if(resultCode == 1) { // 로직 성공
                // Toast.makeText(mContext, "인증 성공함 : " + data.getStringExtra("auth"), Toast.LENGTH_SHORT).show();
                Snackbar.make(mainLayout,"인증 성공", 1000).show();
            } else { // 로직 실패
                Toast.makeText(mContext, "인증 실패", Toast.LENGTH_SHORT).show();
            }
        }
    }
}