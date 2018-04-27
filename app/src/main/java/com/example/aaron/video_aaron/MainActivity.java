package com.example.aaron.video_aaron;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,SurfaceHolder.Callback{

    private String mVideoUrl =
            "https://s3-ap-northeast-1.amazonaws.com/mid-exam/Video/protraitVideo.mp4";
    private SurfaceView mSurfaceView;
    private MediaPlayer mMediaPlayer;
    private SurfaceHolder mSurfaceHolder;
    private AudioManager mAudioManager;

    ImageButton mPlay, mPause, mRewind, mForward, mMute, mUnmute, mFullscreen, mFullscreenExit;
    ConstraintLayout mPlayer;
    SeekBar mSeekBar;
    TextView mTimeCurrent,mTimeTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        mSurfaceHolder = mSurfaceView.getHolder();
//        mSurfaceHolder.addCallback(MainActivity.this);      //Surface lifecycle need to be handle
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
    public void surfaceCreated(SurfaceHolder holder) {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setDisplay(mSurfaceHolder);
        try {
            mMediaPlayer.setDataSource(mVideoUrl);
            mMediaPlayer.prepare();
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.play:{
                mSurfaceHolder = mSurfaceView.getHolder();
                mSurfaceHolder.addCallback(MainActivity.this);
                if(mMediaPlayer==null){
                    mMediaPlayer=new MediaPlayer();
                    mMediaPlayer.setAudioSessionId(AudioManager.STREAM_MUSIC);
                    new MyThread().start();
                    mMediaPlayer.start();
                    int duration=mMediaPlayer.getDuration();
                    mSeekBar.setMax(duration);
                }else{
                    mMediaPlayer.start();
                }
                mPause.setVisibility(View.VISIBLE);
                mPlay.setVisibility(View.INVISIBLE);
                break;
            }
            case R.id.pause:
            {
                if(mMediaPlayer.isPlaying()){
                    mMediaPlayer.pause();
                }
                mPause.setVisibility(View.INVISIBLE);
                mPlay.setVisibility(View.VISIBLE);
                break;
            }
            case R.id.mute:
            {
                mAudioManager= (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
                mMute.setVisibility(View.INVISIBLE);
                mUnmute.setVisibility(View.VISIBLE);
                break;
            }
            case R.id.unmute:
            {
                mAudioManager= (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 5, 0);
                mUnmute.setVisibility(View.INVISIBLE);
                mMute.setVisibility(View.VISIBLE);
                break;
            }
            case R.id.fullscreen:
            {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                mFullscreen.setVisibility(View.INVISIBLE);
                mFullscreenExit.setVisibility(View.VISIBLE);
                break;
            }
            case R.id.fullscreen_exit:
            {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                mFullscreenExit.setVisibility(View.INVISIBLE);
                mFullscreen.setVisibility(View.VISIBLE);
                break;
            }
            case R.id.rewind:
            {
                break;
            }
            case R.id.forward:
            {
                break;
            }

        }
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            while(mSeekBar.getProgress()<=mSeekBar.getMax()){
                int position=mMediaPlayer.getCurrentPosition();
                mSeekBar.setProgress(position);
            }
        }
    }

    private void initUi() {
        mPlay = findViewById(R.id.play);
        mPause = findViewById(R.id.pause);
        mRewind = findViewById(R.id.rewind);
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
        mRewind.setOnClickListener(this);
        mForward.setOnClickListener(this);
        mMute.setOnClickListener(this);
        mUnmute.setOnClickListener(this);
        mFullscreen.setOnClickListener(this);
        mFullscreenExit.setOnClickListener(this);
        mPlayer.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseMediaPlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if(mMediaPlayer != null){
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
