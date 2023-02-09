package com.example.notificaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.os.Bundle;

public class Brujula extends AppCompatActivity {

    private static final int NOTIFICATION_ID=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brujula);

        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(getApplicationContext());

        notificationManagerCompat.cancel(NOTIFICATION_ID);
    }
}