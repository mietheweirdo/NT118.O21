package com.example.lab5_b4;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
                if (mediaPlayer == null) {
                    new PlayMusicTask().execute("https://freetestdata.com/wp-content/uploads/2021/09/Free_Test_Data_1MB_MP3.mp3");
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

    private class PlayMusicTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Downloading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            String url = strings[0];
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepare();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            progressDialog.dismiss();

            if (result) {
                mediaPlayer.start();
            } else {
                Toast.makeText(MainActivity.this, "Failed to load audio", Toast.LENGTH_SHORT).show();
            }
        }
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
