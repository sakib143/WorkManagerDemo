package com.example.workmanagerdemo;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.Random;

public class RandomNumberGeneratorWorker extends Worker {
    Context context;
    WorkerParameters workerParameters;

    private int mRandomNumber;
    private boolean mIsRandomGeneratorOn;


    private final int MIN = 0;
    private final int MAX = 100;

    public RandomNumberGeneratorWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
        this.workerParameters = workerParams;

        mIsRandomGeneratorOn = true;

    }


    private void startRandomNumberGenerator() {
        int i = 0;
        while (i < 5) {
            try {
                Thread.sleep(1000);
                if(mIsRandomGeneratorOn) {
                    mRandomNumber =new Random().nextInt(MAX)+MIN;
                    Log.e("==> ?? ","Thread id: "+Thread.currentThread().getId()+", Random Number: "+ mRandomNumber);
                    i++;
                }
            } catch (Exception e) {
                Log.e("==>"," Exception");
            }
        }
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.e("==>"," Worker doWork() !!! ");
        startRandomNumberGenerator();
        return Result.success();
    }

    @Override
    public void onStopped() {
        super.onStopped();
        Log.e("==>"," Worker get cancelled !!! ");
    }
}
