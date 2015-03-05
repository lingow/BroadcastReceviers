package com.lingoware.broadcastreceviers;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class PhoneCallReceiver extends BroadcastReceiver {
    public PhoneCallReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String state = extras.getString(TelephonyManager.EXTRA_STATE);
            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                String phoneNumber = extras
                        .getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                Toast.makeText(context,"Incoming call from " + phoneNumber,Toast.LENGTH_SHORT).show();
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
                mBuilder.setSmallIcon(android.R.drawable.ic_menu_call);
                mBuilder.setContentTitle("Llamada Entrante");
                mBuilder.setContentText("Telefono: " + phoneNumber);

                Intent in = new Intent(context,ActivityMain.class);

                TaskStackBuilder tsb = TaskStackBuilder.create(context);
                tsb.addParentStack(ActivityMain.class);
                tsb.addNextIntent(in);
                PendingIntent pin = tsb.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

                mBuilder.setContentIntent(pin);

                NotificationManager nm = (NotificationManager)
                        context.getSystemService(Context.NOTIFICATION_SERVICE);
                nm.notify(1,mBuilder.build());

                Log.v("LLAMADAS","Entrante");
            }
            else
            {
                Toast.makeText(context,"State" + state,Toast.LENGTH_SHORT).show();
                Log.v("LLAMADAS", "Idle");

            }
        }
    }
}
