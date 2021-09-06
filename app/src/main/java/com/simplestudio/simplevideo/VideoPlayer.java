package com.simplestudio.simplevideo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import static com.simplestudio.simplevideo.VideoAdaptor.videoFiles;

public class VideoPlayer extends AppCompatActivity {

    PlayerView playerView;
    SimpleExoPlayer simpleExoPlayer;
    int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        getSupportActionBar().hide();

        playerView = findViewById(R.id.exoplayerView);
        position = getIntent().getIntExtra("position",-1);

        String path = videoFiles.get(position).getPath();

        if ( path!= null)
        {
            Uri uri = Uri.parse(path);
            simpleExoPlayer = new SimpleExoPlayer.Builder(this).build();

            DataSource.Factory factory = new DefaultDataSourceFactory(this,
                    Util.getUserAgent(this,"my app"));

            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

            MediaSource mediaSource = new ProgressiveMediaSource.Factory(factory,
                    extractorsFactory).createMediaSource(uri);

            playerView.setPlayer(simpleExoPlayer);
            playerView.setKeepScreenOn(true);
            simpleExoPlayer.setMediaSource(mediaSource);
            simpleExoPlayer.setPlayWhenReady(true);
        }

        if (simpleExoPlayer.isPlaying())
        {
            hideStatusBar();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        simpleExoPlayer.stop();
        simpleExoPlayer.release();
    }

    private void hideStatusBar()
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}