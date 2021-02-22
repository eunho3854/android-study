package com.view.musicapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity2";
    private TextInputEditText tiMusic;
    private Button btnStart,btnStop, btnPause;
    private MediaPlayer mp;

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            Log.d(TAG, "onServiceConnected: 서비스 연결됨");
            mp = ((MyService.LocalBinder) service).getMP();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: ");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 서비스 실행 = 서비스의 onCreate와 onBind실행 -> connection.onServiceConnected가 콜백됨.
        Intent musicIntent = new Intent(MainActivity.this, MyService.class);
        bindService(musicIntent, connection, BIND_AUTO_CREATE);

        tiMusic = findViewById(R.id.ti_music);
        btnStart = findViewById(R.id.btn_start);
        btnStop = findViewById(R.id.btn_stop);
        btnPause = findViewById(R.id.btn_pause);

        btnStart.setOnClickListener(v -> {
            // 음악재생
            mp.start();
        });

        // stop을 하게 되면 mp객체가 날라감. 다시 start가 안 됨.
        // 그래서 pause하고 seekTo 사용
        btnStop.setOnClickListener(v -> {
            mp.pause();
            mp.seekTo(0);
        });

        // nextMusic 버튼


        btnPause.setOnClickListener(v -> {
            mp.pause();
        });
    }
}