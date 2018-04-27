package com.example.aaron.video_aaron;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String mVideoUrl =
            "https://s3-ap-northeast-1.amazonaws.com/mid-exam/Video/protraitVideo.mp4";
    private SurfaceView mSurfaceView;
    private MediaPlayer mMediaPlayer;
    private SurfaceHolder mSurfaceHolder;
    ImageButton mPlay, mPause, mRewing, mForward, mMute, mUnmute, mFullscreen, mFullscreenExit;
    ConstraintLayout mPlayer;
    SeekBar mSeekBar;
    TextView mTimeCurrent,mTimeTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();

        mSurfaceView = new SurfaceView(this);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress=seekBar.getProgress();
                mMediaPlayer.seekTo(progress);
            }
        });

    }

    @Override
    public void onClick(View v) {
        mMediaPlayer = new MediaPlayer();
        switch (v.getId()){
            case R.id.play:{
                mPause.setVisibility(View.VISIBLE);
                mPlay.setVisibility(View.INVISIBLE);
                break;
            }
            case R.id.pause:
            {
                mPause.setVisibility(View.INVISIBLE);
                mPlay.setVisibility(View.VISIBLE);
                break;
            }
            case R.id.mute:
            {
                mMute.setVisibility(View.INVISIBLE);
                mUnmute.setVisibility(View.VISIBLE);
                break;
            }
            case R.id.unmute:
            {
                mUnmute.setVisibility(View.INVISIBLE);
                mMute.setVisibility(View.VISIBLE);
                break;
            }
            case R.id.fullscreen:
            {
                mFullscreen.setVisibility(View.INVISIBLE);
                mFullscreenExit.setVisibility(View.VISIBLE);
                break;
            }
            case R.id.fullscreen_exit:
            {
                mFullscreenExit.setVisibility(View.INVISIBLE);
                mFullscreen.setVisibility(View.VISIBLE);
                break;
            }
            case R.id.rewing:
            {
                break;
            }
            case R.id.forward:
            {
                break;
            }

        }
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
        mTimeCurrent = findViewById(R.id.time_current);
        mTimeTotal = findViewById(R.id.time_total);
        mSeekBar = findViewById(R.id.seekBar);

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
