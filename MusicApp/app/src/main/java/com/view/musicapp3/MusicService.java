package com.view.musicapp3;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class MusicService extends Service {

    private MediaPlayer mp;
    private final IBinder mBinder = new LocalBinder();

    class LocalBinder extends Binder {
        MusicService getService() {
            return MusicService.this;
        }
    }

    public MusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(this, R.raw.sample1);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public MediaPlayer getMediaPlayer(){
        return mp;
    }

}