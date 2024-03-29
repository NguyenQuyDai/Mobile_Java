package com.example.bai_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;
    int level , scale , temperature , voltage , health , status;
    String technology;
    float batteryPct;
    IntentFilter intentFilter;
    Intent batteryStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        batteryStatus = getApplicationContext().registerReceiver(null,intentFilter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBatteryInfos();
            }
        });
    }
    private void getBatteryInfos()
    {
        level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
        scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
        // tinh % pin
        batteryPct = level*100/(float) scale;
        // Nhiet do pin
        temperature = batteryStatus.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,-1);
        // Tra ve mili vol => chia 1000
        voltage = batteryStatus.getIntExtra(BatteryManager.EXTRA_VOLTAGE,-1);
        // Tinh trang pin
        health = batteryStatus.getIntExtra(BatteryManager.EXTRA_HEALTH,-1);
        // cong nghe pin
        status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
        // cong nghe pin
        technology = batteryStatus.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY,"null");
        textView.setText("Pin :"+batteryPct+"%\n"+
                "Nhiệt độ:"+(temperature/10.0)+"C\n"+
                "Hiệu điện thế: "+voltage/100.0+"V\n"+
                "Tình trạng pin:"+health+"\n"+
                "Trạng thái:"+status+"\n"+
                "Công nghệ pin:"+technology+"\n"+
                "Dung lương pin:"+getBatteryCapacity()+"mah.");
    }
    // Hàm tính dung lương pin
    public  Double getBatteryCapacity()
    {
        Object o = null;
        double batteryCapacity = 0;
        final String POWER_PROFILE_CLASS ="com.android.internal.os.PowerProfile";
        try {
            o = Class.forName(POWER_PROFILE_CLASS).getConstructor(Context.class).newInstance(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            batteryCapacity = (Double) Class.forName(POWER_PROFILE_CLASS).getMethod("getAveragePower",java.lang.String.class).invoke(o,"battery.capacity");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(MainActivity.this, batteryCapacity+"mah", Toast.LENGTH_SHORT).show();
        return batteryCapacity;
    }
}