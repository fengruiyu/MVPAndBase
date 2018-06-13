package com.example.textbase.app;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "875faea25a", false);
    }
}
