package com.example.batterylisten;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;

public class BatteryLevelReceiver extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_level_receiver);
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = registerReceiver(null, ifilter);
        int status = batteryStatus.getIntExtra(
                BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING;
        int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float batteryPct = level / (float)scale;

        Log.i("BatteryLevelReceiver", "isCharging:" + isCharging + "；" + "usbCharge:" + usbCharge + "；" + "acCharge:" + acCharge);
        if(isCharging==true){
            Log.d("BatteryLevelReceiver","正在充电" );
        }
        Log.i("BatteryLevelReceiver","电量："+batteryPct);
        if(batteryPct<=0.2){
            Log.d("BatteryLevelReceiver","电量较低" );
        }
        if(batteryPct>0.2){
            Log.d("BatteryLevelReceiver","电量充足" );
        }
    }

}