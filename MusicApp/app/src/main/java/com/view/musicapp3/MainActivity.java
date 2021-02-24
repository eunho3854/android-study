package com.view.musicapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private MusicService musicService;
    private MediaPlayer mp;

    private ImageView btnPlayStop;
    private TextView tvTime;
    private SeekBar seekBar;

    Handler handler = new Handler();
    private Thread uiHandleThread;
    private boolean threadStatus = false;
    private boolean isPlaying = false;

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            MusicService.LocalBinder localBinder = (MusicService.LocalBinder) service;
            musicService = localBinder.getService();
            mp = musicService.getMediaPlayer();
            seekBarInit();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mp.stop();
            mp.release();
        }
    };

    public void seekBarInit() {
        seekBar.setMax(mp.getDuration());
        seekBar.setProgress(0);
    }

    public void musicStart() {
        mp.start();
        btnPlayStop.setImageResource(R.drawable.ic_pause);
    }

    public void musicPause() {
        mp.pause();
        btnPlayStop.setImageResource(R.drawable.ic_play);
    }

    public void musicStop() {
        mp.seekTo(0);
        btnPlayStop.setImageResource(R.drawable.ic_play);
        seekBar.setProgress(0);
        threadStatus = true;
        isPlaying = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        btnPlayStop = findViewById(R.id.btn_play_stop);
        seekBar = findViewById(R.id.seekBar);
        tvTime = findViewById(R.id.tv_time);

        Intent musicIntent = new Intent(MainActivity.this, MusicService.class);
        bindService(musicIntent, connection, BIND_AUTO_CREATE);

        btnPlayStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isPlaying = !isPlaying;

                if (isPlaying == true) {
                    musicStart();
                } else {
                    musicPause();
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (isPlaying == true) {

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    seekBar.setProgress(mp.getCurrentPosition());

                                    if (mp.getCurrentPosition() >= mp.getDuration()) {
                                        musicStop();
                                    }
                                }
                            });
                            try {
                                Thread.sleep(1000);
                                if (threadStatus) {
                                    uiHandleThread.interrupt();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();

                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                        if (fromUser) {
                            mp.seekTo(progress);
                        }

                        int m = progress / 60000;
                        int s = (progress % 60000) / 1000;
                        String strTime = String.format("%02d:%02d", m, s);
                        tvTime.setText(strTime);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
            }
        });
    }
}