package com.example.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

public class Vi_du_BroadCast_Reveicer extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction()))
        {
            if(isNetworkAvailable())
            {
                Toast.makeText(context, "INTERNET CONNECTED", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context, "INTERNET DISCONNECTED", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private boolean isNetworkAvailable(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        if(connectivityManager == null)
            return false;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            Network network = connectivityManager.getActiveNetwork();
            if(network==null)
            {
                return false;
            }
            NetworkCapabilities networkCapabilities = 
            else
        }else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null&&networkInfo.isConnected();
        }
    }
}
