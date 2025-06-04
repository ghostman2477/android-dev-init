package com.example.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;


public class SplashScreen extends AppCompatActivity {
    private SurfaceView surfaceView;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        surfaceView = findViewById(R.id.videoSurface);
        SurfaceHolder holder = surfaceView.getHolder();

        // Get screen dimensions
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        int screenHeight = metrics.heightPixels;

        // Calculate aspect ratio (Width / Height)
        float aspectRatio = (float) screenWidth / screenHeight;

        // Set SurfaceView dimensions dynamically
        ViewGroup.LayoutParams params = surfaceView.getLayoutParams();
        params.width = screenWidth;
        params.height = (int) (screenWidth / aspectRatio); // Adjust height based on ratio
        surfaceView.setLayoutParams(params);

        // Set video playback
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                mediaPlayer = MediaPlayer.create(SplashScreen.this,
                        android.net.Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splashscreen));
                mediaPlayer.setDisplay(holder);
                mediaPlayer.start();

                // Transition based on aspect ratio
                 // Example threshold for wide screens
                mediaPlayer.setOnCompletionListener(mp -> {
                    startActivity(new Intent(SplashScreen.this, IceCreamActivity.class));
                    finish();
                });


            }


            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
            }
        });

    }
}
