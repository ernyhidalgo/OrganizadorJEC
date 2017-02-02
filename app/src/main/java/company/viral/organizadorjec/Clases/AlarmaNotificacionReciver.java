package company.viral.organizadorjec.Clases;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import company.viral.organizadorjec.ActivitysPrincipales.MainActivity;
import company.viral.organizadorjec.R;

/**
 * Created by Genesis on 29/01/2017.
 */

public class AlarmaNotificacionReciver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher).setContentTitle("Recordatorio")
                .setContentText("Recuerda tienes trabajo")
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                .setContentInfo("info");


        NotificationManager notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());
    }
}
