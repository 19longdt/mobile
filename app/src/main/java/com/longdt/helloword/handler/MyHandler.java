package com.longdt.helloword.handler;


import android.app.Service;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MyHandler extends Handler {

    private Service service;
    public MyHandler(@NonNull Looper looper, Service service) {
        super(looper);
        this.service = service;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        try {
            FileOutputStream fos = service.openFileOutput("data.txt", Context.MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            for (int i = 1; i <= 20; i++) {
                writer.write(String.valueOf(i));
                writer.newLine();
                Thread.sleep(1000);
            }
            writer.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
