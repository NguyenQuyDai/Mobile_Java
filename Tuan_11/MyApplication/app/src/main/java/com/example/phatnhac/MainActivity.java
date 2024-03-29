package com.example.phatnhac;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView videoView;
    MediaController mediaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = findViewById(R.id.videoView);

        String videoPath = "android.resource://"+getPackageName()+"/"+R.raw.mov_bbb;

        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        if(mediaController == null){
            mediaController = new MediaController(MainActivity.this);
        }
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
    }
}