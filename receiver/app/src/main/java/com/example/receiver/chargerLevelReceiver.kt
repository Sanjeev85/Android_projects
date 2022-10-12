package com.example.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.widget.TextView
import android.widget.Toast

class chargerLevelReceiver(val tv: TextView, val context: Context) : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        val batteryStatus = p1?.getIntExtra(BatteryManager.EXTRA_STATUS, 0)
        val isCharging: Boolean =
            batteryStatus == BatteryManager.BATTERY_STATUS_CHARGING ||
                    batteryStatus == BatteryManager.BATTERY_STATUS_FULL

        if (isCharging) {
            Toast.makeText(context, "Battery is Charging...", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Not Charging...", Toast.LENGTH_SHORT).show()
        }
    }
}