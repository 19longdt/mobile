package com.longdt.helloword.mutilThread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.longdt.helloword.R;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class MutilThread extends AppCompatActivity {

    private ImageView img;
    private ProgressBar bar;
    private Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutilthread);

        img = findViewById(R.id.imgProbar);
        bar = findViewById(R.id.proBar);
        btn = findViewById(R.id.btnLoad);

        btn.setOnClickListener(view -> {
            downloadImageAsyncTask downloadImageAsyncTask = new downloadImageAsyncTask();
            downloadImageAsyncTask.execute("https://upload.wikimedia.org/wikipedia/commons/9/9a/Gull_portrait_ca_usa.jpg");
        });

    }

    private class downloadImageAsyncTask extends AsyncTask<String, Integer, Bitmap>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            bar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            img.setImageBitmap(bitmap);
            bar.setVisibility(View.GONE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            bar.setProgress(values[0]);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try{
                URL url = new URL(strings[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                int lengthOfFile = connection.getContentLength();
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                byte data[] = new byte[1024];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    publishProgress((int) ((total * 100) / lengthOfFile));
                }
                Bitmap image = BitmapFactory.decodeStream(url.openStream());

                return image;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
