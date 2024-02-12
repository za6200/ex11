package com.example.ex11;

import static android.content.Context.MODE_PRIVATE;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.ex11.MainActivity;

public class HeadPhonesReceiver extends BroadcastReceiver {

    private final String THC_REC = "com.example.ex11_2024.Receivers.CustomHPReciever";
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences hc = context.getSharedPreferences("bootcount", MODE_PRIVATE);
        SharedPreferences.Editor edit=hc.edit();
        int temp  = hc.getInt("hcon", -1);
        if(temp != -1)
        {
            edit.putInt("hcon", temp + 1);
        }
        else
        {
            edit.putInt("hcon",1);
        }
        if(temp % 3 == 0)
        {
            custombrod(context);
        }
        edit.commit();
        MainActivity.getInstance().update_headphone("Headphones connections:  "+(temp+1));

    }

    private void custombrod(Context context) {
        Intent intent = new Intent(THC_REC);
        context.sendBroadcast(intent);
    }
}