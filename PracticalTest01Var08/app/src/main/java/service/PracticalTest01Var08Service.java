package service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PracticalTest01Var08Service extends Service {

    private ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //int firstNumber = intent.getIntExtra("firstNumber", -1);
        //int secondNumber = intent.getIntExtra("secondNumber", -1);
        Log.d("service started", "service started");
        processingThread = new ProcessingThread(getBaseContext());
        processingThread.start();
        Log.d("service started 2", "service started");
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }
}
