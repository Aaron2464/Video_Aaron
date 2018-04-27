package com.example.aaron.video_aaron;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String mVideoUrl =
            "https://s3-ap-northeast-1.amazonaws.com/mid-exam/Video/protraitVideo.mp4";
    private SurfaceView mSurfaceView;
    private MediaPlayer mMediaPlayer;
    private SurfaceHolder mSurfaceHolder;
    ImageButton mPlay, mPause, mRewing, mForward, mMute, mUnmute, mFullscreen, mFullscreenExit;
    ConstraintLayout mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
    }

    @Override
    public void onClick(View v) {
       
    }
    private void initUi() {
        mPlay = findViewById(R.id.play);
        mPause = findViewById(R.id.pause);
        mRewing = findViewById(R.id.rewing);
        mForward = findViewById(R.id.forward);
        mMute = findViewById(R.id.mute);
        mUnmute = findViewById(R.id.unmute);
        mFullscreen = findViewById(R.id.fullscreen);
        mFullscreenExit = findViewById(R.id.fullscreen_exit);
        mSurfaceView = findViewById(R.id.surfaceView);
        mPlayer = findViewById(R.id.player);
        mSurfaceView = new SurfaceView(this);
        mSurfaceHolder = mSurfaceView.getHolder();

        mPlay.setOnClickListener(this);
        mPause.setOnClickListener(this);
        mRewing.setOnClickListener(this);
        mForward.setOnClickListener(this);
        mMute.setOnClickListener(this);
        mUnmute.setOnClickListener(this);
        mFullscreen.setOnClickListener(this);
        mFullscreenExit.setOnClickListener(this);
        mPlayer.setOnClickListener(this);
    }
}
