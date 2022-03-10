package com.longdt.helloword.service;

import android.app.Service;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;

import com.longdt.helloword.handler.MyHandler;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MyService extends Service {

    private MyHandler myHandler;
    private Looper looper;

    @Override
    public void onCreate() {
        super.onCreate();
        HandlerThread handlerThread = new HandlerThread("MyThread", Process.THREAD_PRIORITY_BACKGROUND);
        handlerThread.start();
        looper = handlerThread.getLooper();
        myHandler = new MyHandler(looper, this);
    }

    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Message mesg = myHandler.obtainMessage();
        mesg.arg1 = startId;
        myHandler.sendMessage(mesg);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}