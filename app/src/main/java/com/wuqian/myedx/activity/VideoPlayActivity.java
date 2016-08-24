package com.wuqian.myedx.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;

import com.wuqian.myedx.R;
import com.wuqian.myedx.util.ToastUtils;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class VideoPlayActivity extends AppCompatActivity {
    private String title,url;
    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
        Vitamio.isInitialized(getApplicationContext());
        setContentView(R.layout.activity_video_play);
        title=getIntent().getStringExtra("title");
        url=getIntent().getStringExtra("url");
        initView();
        playfunction();
    }

    private void initView(){
        videoView= (VideoView) findViewById(R.id.video_view);

        /*videoView= (VideoView) findViewById(R.id.video_view);
        try{
            Uri uri=Uri.parse(url);
            videoView.setMediaController(new MediaController(this));
            videoView.setVideoURI(uri);
            videoView.start();
            videoView.requestFocus();
        }catch (Exception e){
            ToastUtils.showToast("无法解析资源地址");
            finish();
        }*/
    }




    private void playfunction(){
        if(TextUtils.isEmpty(url)){
            ToastUtils.showToast("视频地址无法解析");
            return;
        }


        videoView.setVideoLayout(VideoView.VIDEO_LAYOUT_STRETCH, 0);
        videoView.setVideoPath(url);
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                // optional need Vitamio 4.0
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}
