package com.example.workmanagerdemo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class NotificationWorker  extends Worker {

    private static final String WORK_RESULT = "work_result";
    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }
    @NonNull
    @Override
    public Result doWork() {

        Data taskData = getInputData();
//        String taskDataString = taskData.getString(MainActivity.MESSAGE_STATUS);

        String taskDataString = " Testing by Sakib Syed ";
        showNotification("WorkManager", taskDataString != null ? taskDataString : "Message has been Sent");
        Log.e("=> ","doWork() is calling !!!");

//        Intent intent = new Intent(getApplicationContext(),
//                getApplicationContext().getClass()); //getActivity() is undefined!
//        PendingIntent sender = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
//        PackageInstaller mPackageInstaller =
//                getApplicationContext().getPackageManager().getPackageInstaller();
//        mPackageInstaller.uninstall("com.example.appblockerdemo", sender.getIntentSender());

        Data outputData = new Data.Builder().putString(WORK_RESULT, "Jobs Finished").build();
        return Result.success(outputData);

    }

    private void showNotification(String task, String desc) {
        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        String channelId = "task_channel";
        String channelName = "task_name";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new
                    NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                .setContentTitle(task)
                .setContentText(desc)
                .setSmallIcon(R.mipmap.ic_launcher);
        manager.notify(1, builder.build());
    }

}