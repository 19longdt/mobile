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

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MutilThread extends AppCompatActivity {

    private ImageView img;
    private ProgressBar bar;
    private Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        img = findViewById(R.id.imgProbar);
        bar = findViewById(R.id.proBar);
        bar.setVisibility(View.GONE);
        btn = findViewById(R.id.btnLoad);

        btn.setOnClickListener(view -> {
            downloadImageAsyncTask asyncTask = new downloadImageAsyncTask();
            asyncTask.execute("https://tophinhanhdep.com/wp-content/uploads/2021/10/2560X1440-Red-Gaming-Wallpapers.jpg");
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
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                Bitmap image = BitmapFactory.decodeStream(inputStream, null, options);
                int per = 0;
                while(per < 100){
                    publishProgress(per++);
                    Thread.sleep(100);
                }

                return image;
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
}
