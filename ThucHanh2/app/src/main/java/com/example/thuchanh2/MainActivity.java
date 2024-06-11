package com.example.thuchanh2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);

        button1.setOnClickListener(v ->
                startActivity(
                        new Intent(MainActivity.this, Bai1.class)));
        button2.setOnClickListener(v ->
                startActivity(
                        new Intent(MainActivity.this, Bai2.class)));
        button3.setOnClickListener(v ->
                startActivity(
                        new Intent(MainActivity.this, Bai3.class)));
        button4.setOnClickListener(v ->
                startActivity(
                        new Intent(MainActivity.this, Bai4.class)));
        button5.setOnClickListener(v ->
                startActivity(
                        new Intent(MainActivity.this, Bai5.class)));
        button6.setOnClickListener(v ->
                startActivity(
                        new Intent(MainActivity.this, Bai6.class)));
        button7.setOnClickListener(v ->
                startActivity(
                        new Intent(MainActivity.this, Bai7.class)));
    }
}