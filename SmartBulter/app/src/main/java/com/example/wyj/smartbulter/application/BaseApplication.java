package com.example.wyj.smartbulter.application;

import android.app.Application;

import com.example.wyj.smartbulter.utils.StaticClass;
import com.tencent.bugly.crashreport.CrashReport;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化bugly
        CrashReport.initCrashReport(getApplicationContext(), StaticClass.BUGLY_APP_ID, true);
    }
}
