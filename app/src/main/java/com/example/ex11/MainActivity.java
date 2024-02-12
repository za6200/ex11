package com.example.ex11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv1, tv2, tv3;
    int temp;
    private static MainActivity ins;

    private HeadPhonesReceiver hc;
    private CustomReceiver CusomR;
    private final String THC_REC = "com.example.ex11_2024.Receivers.CustomHPReciever";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        hc = new HeadPhonesReceiver();
        CusomR = new CustomReceiver();

        ins = this;

        SharedPreferences bootcounter = getSharedPreferences("bootcount", MODE_PRIVATE);
        temp = bootcounter.getInt("boot", -1);
        tv1.setText("Device Activations: " + temp);
        temp = bootcounter.getInt("hcon", 0);
        tv2.setText("Headphones connections:  " + temp);
        temp = bootcounter.getInt("thcon", 0);
        tv3.setText("Headphones connections:  " + temp);


    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter cf = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(hc, cf);
        IntentFilter tcf = new IntentFilter(THC_REC);
        registerReceiver(CusomR, tcf);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(hc);
        unregisterReceiver(CusomR);
    }

    public void update_headphone(final String t) {
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                tv2.setText(t);
            }
        });

    }
    public static MainActivity  getInstance(){
        return ins;
    }

    public void update_theadphone(String t) {
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run()
            {
                tv3.setText(t);
            }
        });
    }
}