package com.example.ex11;

import static android.content.Context.MODE_PRIVATE;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class CustomReceiver extends BroadcastReceiver {
    private final String THC_REC = "com.example.ex11_2024.Receivers.CustomHPReciever";
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(THC_REC)){
            SharedPreferences hc = context.getSharedPreferences("bootcount", MODE_PRIVATE);
            SharedPreferences.Editor edit=hc.edit();
            int temp  = hc.getInt("thcon", -1);
            if(temp!=-1) {

                edit.putInt("thcon", temp + 1);
            }
            else
            {
                edit.putInt("thcon",1);
            }
            edit.commit();
            MainActivity.getInstance().update_theadphone("Headphones connections:  "+(temp+1));
        }
    }
}