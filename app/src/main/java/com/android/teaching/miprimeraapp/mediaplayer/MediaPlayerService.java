package com.android.teaching.miprimeraapp.mediaplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import com.android.teaching.miprimeraapp.R;

public class MediaPlayerService extends Service {
    public MediaPlayerService() {
    }

    private MediaPlayer myMediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        myMediaPlayer = MediaPlayer.create(this, R.raw.marchaimperial);
        myMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopSelf();
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent,
                              int flags, int startId) {

        //Intent myIntent = new Intent(this, MediaPlayerService.class);
       // startService(myIntent);
        if(myMediaPlayer.isPlaying()){
        myMediaPlayer.pause();}
        else {
            myMediaPlayer.start();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myMediaPlayer.release();
        myMediaPlayer = null;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}
