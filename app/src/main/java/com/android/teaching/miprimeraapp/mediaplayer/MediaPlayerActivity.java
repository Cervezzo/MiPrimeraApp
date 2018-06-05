package com.android.teaching.miprimeraapp.mediaplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.android.teaching.miprimeraapp.R;

import java.net.URI;


public class MediaPlayerActivity extends AppCompatActivity {

    private MediaPlayer myMediaPlayer;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);

        videoView = findViewById(R.id.video_view);

        videoView.setVideoURI(Uri.parse(
                "https://img-9gag-fun.9cache.com/photo/aBxGoNN_460sv.mp4"));

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.start();

        Toolbar toolbarPlayer = findViewById(R.id.toolbarPlayer);
        setSupportActionBar(toolbarPlayer);




        //  myMediaPlayer = MediaPlayer.create(this, R.raw.starwars);

    }

    @Override
    protected void onStop() {
        super.onStop();
       // myMediaPlayer.release();
       // myMediaPlayer = null;
    }

    public void onPauseMusic(View view) {
        //    myMediaPlayer.pause();
        Intent myIntent = new Intent(this, MediaPlayerService.class);
        stopService(myIntent);
    }

    public void onPlay(View view) {
        //  myMediaPlayer.start();
        Intent myIntent = new Intent(this, MediaPlayerService.class);
        startService(myIntent);

    }
}
