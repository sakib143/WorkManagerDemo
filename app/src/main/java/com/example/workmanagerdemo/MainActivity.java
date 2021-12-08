package com.example.workmanagerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.impl.WorkManagerImpl;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private Button btnStartService, btnEndService;
    private TextView tvThreadCound;
    private boolean mStopLoop;


    private WorkManager workManager;
    private WorkRequest workRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEndService = findViewById(R.id.btnEndService);
        btnStartService = findViewById(R.id.btnStartService);
        tvThreadCound = findViewById(R.id.tvThreadCound);


        workManager = WorkManager.getInstance(MainActivity.this);
        workRequest = new PeriodicWorkRequest.Builder(RandomNumberGeneratorWorker.class, 15, TimeUnit.MINUTES).build();

        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStopLoop = true;
                workManager.enqueue(workRequest);
            }
        });

        btnEndService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workManager.cancelWorkById(workRequest.getId());
            }
        });

    }

}