package com.example.facedoor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.aispeech.ailog.AILog;
import com.aispeech.dui.dds.DDS;

public class LauncherActivity extends Activity {

    private static final String TAG = "LauncherActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        new Thread() {
            public void run() {
                checkDDSReady();
        }
        }.start();
    }


    public void checkDDSReady() {
        while (true) {
            if (DDS.getInstance().isInitComplete()) {
                Intent intent = new Intent(getApplicationContext(), IndexActivity.class);
                startActivity(intent);
                finish();
                break;
            } else {
                AILog.w(TAG, "waiting  init complete finish...");
            }
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
