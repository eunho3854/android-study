package com.view.musicapp2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {

    // 생명주기 상관없이 1순위로 읽음
    private static final String TAG = "MyService";
    private MediaPlayer mp;
    private final IBinder mBinder = new LocalBinder();

    class LocalBinder extends Binder {
        MediaPlayer getMP() {
            return mp;
        }
    }

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(this, R.raw.sample1);
    }

    @Override
    public IBinder onBind(Intent intent) { // 바인드를 한단는 건 MainActivity에서 서비스 제어가 가능함.
        // 서비스 실행 직전에 실행됨.

        return mBinder;
    }
}