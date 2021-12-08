package com.example.workmanagerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private Button btnStartService, btnEndService;
    private TextView tvThreadCound;
    private boolean mStopLoop;


    private WorkManager workManager;
    private WorkRequest workRequest1,workRequest2,workRequest3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getIds();
        setWorkManager();

        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStopLoop = true;
                //If we want to perform multiple workrequest sequencly then
//                workManager.beginWith((OneTimeWorkRequest) workRequest1).then((OneTimeWorkRequest)workRequest2).then((OneTimeWorkRequest)workRequest3).enqueue();
                //If we want to perform two work request first parrallelly then next the use below code.
                workManager.beginWith(Arrays.asList((OneTimeWorkRequest)workRequest1, (OneTimeWorkRequest)workRequest2)).then((OneTimeWorkRequest)workRequest3).enqueue();
            }
        });

        btnEndService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workManager.cancelAllWorkByTag("worker3");
            }
        });
    }

    private void setWorkManager() {
        workManager = WorkManager.getInstance(MainActivity.this);
        workRequest1 = new OneTimeWorkRequest.Builder(RandomNumberGeneratorWorker1.class).addTag("worker1").build();
        workRequest2 = new OneTimeWorkRequest.Builder(RandomNumberGeneratorWorker2.class).addTag("worker2").build();
        workRequest3 = new OneTimeWorkRequest.Builder(RandomNumberGeneratorWorker3.class).addTag("worker3").build();
    }

    private void getIds() {
        btnEndService = findViewById(R.id.btnEndService);
        btnStartService = findViewById(R.id.btnStartService);
        tvThreadCound = findViewById(R.id.tvThreadCound);
    }

}