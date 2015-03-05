package com.lingoware.broadcastreceviers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.Toast;

public class WifiReceiver extends BroadcastReceiver {
    public WifiReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        NetworkInfo myInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
        if (myInfo != null){
            if(myInfo.isConnected()){
                WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wi = wm.getConnectionInfo();
                Toast.makeText(context,"Connected to " + wi.getSSID(),Toast.LENGTH_SHORT).show();
            }
        }
    }
}
