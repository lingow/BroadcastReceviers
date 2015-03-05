package com.lingoware.broadcastreceviers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast;

public class PowerReceiver extends BroadcastReceiver {
    public PowerReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;
        int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
        Toast.makeText(context, "Status: " + ((isCharging)?"charging":"not charging") +
                                "\nplug: " + ((usbCharge)?"USB":((acCharge)?"AC":"Unknown"+
                                "\nStatus#: " + status +
                                "\nchargePlug#: " + chargePlug )),
                Toast.LENGTH_SHORT).show();
    }
}
