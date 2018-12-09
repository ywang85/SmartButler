package com.example.wyj.smartbulter.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.wyj.smartbulter.MainActivity;
import com.example.wyj.smartbulter.R;
import com.example.wyj.smartbulter.utils.ShareUtils;
import com.example.wyj.smartbulter.utils.StaticClass;
import com.example.wyj.smartbulter.utils.UtilTools;

public class SplashActivity extends AppCompatActivity {

    private TextView textView;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case StaticClass.HANDLER_SPLASH:
                    // 判断是不是滴一次运行
                    if (isFirst()) {
                        startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                    } else {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    }
                    finish();
                    break;
            }
        }
    };

    private boolean isFirst() {
        boolean isFirst = ShareUtils.getBoolean(this, StaticClass.SHARE_IS_FIRST, true);
        if (isFirst) {
            ShareUtils.putBoolean(this, StaticClass.SHARE_IS_FIRST, false);
            // 是第一次
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
    }

    private void initView() {
        // 延时2s
        handler.sendEmptyMessageDelayed(StaticClass.HANDLER_SPLASH, 2000);
        textView = findViewById(R.id.tv_splash);
        // 设置字体
        UtilTools.setFont(this, textView);
    }

    // 禁止返回键
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
