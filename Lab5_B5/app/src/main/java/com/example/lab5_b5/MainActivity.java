package com.example.lab5_b5;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Button playButton;
    private Button pauseButton;
    private MediaPlayer mediaPlayer;
    private boolean isPaused = false;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButton);
        pauseButton = findViewById(R.id.pauseButton);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer == null || !mediaPlayer.isPlaying()) {
                    playMusic();
                } else if (isPaused) {
                    mediaPlayer.start();
                    isPaused = false;
                }
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    isPaused = true;
                }
            }
        });
    }

    @SuppressLint("CheckResult")
    private void playMusic() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Downloading...");
        progressDialog.show();

        Observable.create((ObservableOnSubscribe<MediaPlayer>) emitter -> {
                    mediaPlayer = MediaPlayer.create(this, R.raw.music);
                    emitter.onNext(mediaPlayer);
                    emitter.onComplete();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> progressDialog.dismiss())
                .subscribe(mediaPlayer -> {
                    mediaPlayer.start();
                }, throwable -> {
                    Toast.makeText(MainActivity.this, "Failed to load audio", Toast.LENGTH_SHORT).show();
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
