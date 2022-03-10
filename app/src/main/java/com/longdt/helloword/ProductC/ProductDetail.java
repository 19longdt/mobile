package com.longdt.helloword.ProductC;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.longdt.helloword.Home;
import com.longdt.helloword.LoginActivity;
import com.longdt.helloword.R;
import com.longdt.helloword.entity.Product;

import java.util.List;

public class ProductDetail extends AppCompatActivity {
    private TextView txtName;
    private TextView txtInfo;
    private ImageView imgView;
    private Button btnBuy;

    private final String NOTIFICATION_CHANNEL_ID = "Facebook";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        txtName = findViewById(R.id.txtProductName);
        txtInfo = findViewById(R.id.txtAmountPrice);
        imgView = findViewById(R.id.imgPro);
        btnBuy = findViewById(R.id.btnBuy);

        Intent intent = getIntent();

        Product product = (Product) intent.getSerializableExtra("product");
        imgView.setImageResource(product.getImg());

        txtName.setText(product.getName());
        txtInfo.setText(product.getAmount() + " " + product.getPrice());

        btnBuy.setOnClickListener(view -> {
            sendNotify("buy " + txtName.getText().toString() + " successfully! ThankYou!");
        });
    }

    private void sendNotify(String message){
        Intent intent = new Intent(this,ProductDetail.class);
        intent.putExtra("notification_content",message);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,1,intent,PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);


        Notification notification = builder
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.bi_an_dang_sau_logo_cua_facebook)
                .setContentTitle("Facebook")
                .setContentText(message)
                .setAutoCancel(true)
                .build();
        NotificationManagerCompat manager = NotificationManagerCompat.from(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new
                    NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    "Kênh bắn noti của IS1426",
                    NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(notificationChannel);
        }

        manager.notify(1, notification);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_logout: {
                SharedPreferences preferences = getSharedPreferences("account", Context.MODE_PRIVATE);
                preferences.edit().clear();
                preferences.edit().apply();
                Intent intent = new Intent(ProductDetail.this, LoginActivity.class);
                startActivity(intent);
                break;
            }
//            case R.id.option_fav:{
//                Toast.makeText(this, "fav", Toast.LENGTH_SHORT).show();
//                break;
//            }
        }
        return super.onOptionsItemSelected(item);
    }
}
