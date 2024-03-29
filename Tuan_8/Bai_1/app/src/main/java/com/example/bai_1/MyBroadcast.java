package com.example.bai_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        TextView tvStatus = ((MainActivity)context).findViewById(R.id.tvStatus);
        ImageView imgStatus = ((MainActivity)context).findViewById(R.id.imgStatus);
        if(isAirPlaneModeOn(context))
        {
            Toast.makeText(context, "AIR PLANE MODE ON", Toast.LENGTH_LONG).show();
            tvStatus.setText("AIR PLANE MODE ON");
            imgStatus.setImageResource(R.drawable.airphone_mode);
        }
        else {
            Toast.makeText(context, "AIR PLANE MODE OFF", Toast.LENGTH_SHORT).show();
            tvStatus.setText("AIR PLANE MODE OFF");
            imgStatus.setImageResource(R.drawable.wifi);
        }
    }
    private boolean isAirPlaneModeOn(Context context)
    {
        int modeOn = Settings.System.getInt(context.getContentResolver(),Settings.Global.AIRPLANE_MODE_ON,0); // Tra ve 0 la tat / khac 0 la bat
        if(modeOn!=0)
        {
            return true;
        }
        else {
            return false;
        }
    }
}
