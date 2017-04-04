package service;

/**
 * Created by student on 04.04.2017.
 */

import java.util.Date;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;

public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;

    //private Random random = new Random();
    Random rand;

    // nextInt is normally exclusive of the top value,
    // so add 1 to make it inclusive


    private int val1;
    private int val2;
    private int val3;
    private int val4;
    private int max = 10;
    private int min = 0;

    public ProcessingThread(Context context) {
        Log.d("[ProcessingThread]", " entered constructor");
        this.context = context;

        val1 =  rand.nextInt((max - min) + 1) + min;
        val2 =  rand.nextInt((max - min) + 1) + min;
        val3 =  rand.nextInt((max - min) + 1) + min;
        val4 =  rand.nextInt((max - min) + 1) + min;
        //arithmeticMean = (firstNumber + secondNumber) / 2;
        //geometricMean = Math.sqrt(firstNumber * secondNumber);
        Log.d("[ProcessingThread]", "exited constructor");
    }

    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("[ProcessingThread]", "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction("random_action");
        intent.putExtra("val1", val1);
        intent.putExtra("val2", val2);
        intent.putExtra("val3", val3);
        intent.putExtra("val4", val4);


        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}