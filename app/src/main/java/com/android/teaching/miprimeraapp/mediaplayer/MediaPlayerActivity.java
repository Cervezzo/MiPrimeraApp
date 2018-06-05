package com.android.teaching.miprimeraapp.mediaplayer;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.android.teaching.miprimeraapp.R;


public class MediaPlayerActivity extends AppCompatActivity {


    private VideoView videoView;
    private ProgressBar counterProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);

        counterProgressBar = findViewById(R.id.counter_progress_bar);

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
        new ContadorAsyncTask().execute();

    }

    //@Override
   // protected void onStop() {
    //    super.onStop();
        // myMediaPlayer.release();
        // myMediaPlayer = null;
   // }

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


    //CLASE ASYNCTASK

    private class ContadorAsyncTask extends AsyncTask<Void, Integer, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            counterProgressBar.setMax(100);
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            int count;
            for (count = 0; count <= 100; count++) {
                publishProgress(count);
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return 100;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            counterProgressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Log.d("AsyncTask", "onPostExecute: " + integer.toString());
        }
    }


}
