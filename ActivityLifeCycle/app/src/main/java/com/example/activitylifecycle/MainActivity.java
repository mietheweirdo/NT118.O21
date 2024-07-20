package com.example.activitylifecycle;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String STATE = "Trang thai";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e(STATE, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(STATE, "onStart Called");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.e(STATE, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.e(STATE, "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.e(STATE, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.e(STATE, "onDestroy");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.e(STATE, "onRestoreInstanceState");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.e(STATE, "onSaveInstanceState");
    }
}