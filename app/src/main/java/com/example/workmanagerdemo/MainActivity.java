package com.example.workmanagerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

        getIds();
        setListner();
        setWorkManager();
    }

    private void setListner() {
        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workManager.enqueue(workRequest);
                mStopLoop = true;
            }
        });

        btnEndService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workManager.cancelWorkById(workRequest.getId());
            }
        });
    }

    private void setWorkManager() {
        workManager = WorkManager.getInstance(MainActivity.this);
        workRequest = OneTimeWorkRequest.from(RandomNumberGeneratorWorker.class);
    }

    private void getIds() {
        btnEndService = findViewById(R.id.btnEndService);
        btnStartService = findViewById(R.id.btnStartService);
        tvThreadCound = findViewById(R.id.tvThreadCound);
    }

}