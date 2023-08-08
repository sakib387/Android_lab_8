package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;  // Import the Handler class
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class MainActivity2 extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progress;
    private Handler handler = new Handler();  // Initialize the Handler

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_main2);
        progressBar = findViewById(R.id.progressbar);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
            }
        });
        thread.start();

    }

    public void doWork() {
        for (progress = 20; progress <= 100; progress += 20) {
            try {
                Thread.sleep(1000);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setProgress(progress);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
