package com.example.notificaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.TaskStackBuilder;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button b;
    PendingIntent pendingIntent;
    PendingIntent pendingIntentBrujula;
    PendingIntent pendingIntentGasolinera;


    private static final String CHANNEL_ID="NOTIFICACIÓN";
    private static final int NOTIFICATION_ID=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b=findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Preparo el pending intent para realizar la acción que quiero
                Intent intent=new Intent(getApplicationContext(),Notificacion.class);
                TaskStackBuilder stackBuilder=TaskStackBuilder.create(getApplicationContext());
                stackBuilder.addParentStack(Notificacion.class);
                stackBuilder.addNextIntent(intent);
                pendingIntent=stackBuilder.getPendingIntent(1,PendingIntent.FLAG_UPDATE_CURRENT);

                //Preparo el pending intent para reaccionar a la pulsación del botón Gasolinera
                Intent intent2=new Intent(getApplicationContext(),Gasolinera.class);
                TaskStackBuilder stackBuilder2=TaskStackBuilder.create(getApplicationContext());
                stackBuilder2.addParentStack(Gasolinera.class);
                stackBuilder2.addNextIntent(intent2);
                pendingIntentGasolinera=stackBuilder2.getPendingIntent(1,PendingIntent.FLAG_UPDATE_CURRENT);

                //Preparo el pending intent para reaccionar a la pulsación del botón Gasolinera
                Intent intent3=new Intent(getApplicationContext(),Brujula.class);
                TaskStackBuilder stackBuilder3=TaskStackBuilder.create(getApplicationContext());
                stackBuilder3.addParentStack(Gasolinera.class);
                stackBuilder3.addNextIntent(intent3);
                pendingIntentBrujula=stackBuilder3.getPendingIntent(1,PendingIntent.FLAG_UPDATE_CURRENT);

                //Compruebo si es una versión superior o igual a Andorid 8, para crear o no un canal
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    CharSequence name = "Notificación";
                    int importance = NotificationManager.IMPORTANCE_DEFAULT;
                    NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
                    // Register the channel with the system; you can't change the importance
                    // or other notification behaviors after this
                    NotificationManager notificationManager = getSystemService(NotificationManager.class);
                    notificationManager.createNotificationChannel(channel);
                }


                //Preparamos la notificación
                NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID);
                builder.setSmallIcon(R.drawable.ic_baseline_message_24);
                builder.setContentTitle("Mi notificación");
                builder.setContentText("Has recibido mi notificación");
                builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                builder.setContentIntent(pendingIntent);

                builder.addAction(R.drawable.ic_baseline_explore_24,"Brújula",pendingIntentBrujula);
                builder.addAction(R.drawable.ic_baseline_ev_station_24,"Gasolinera",pendingIntentGasolinera);

                //Lanzar notificación
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
                notificationManager.notify(NOTIFICATION_ID,builder.build());



            }
        });
    }
}