package com.example.wyj.smartbulter.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wyj.smartbulter.MainActivity;
import com.example.wyj.smartbulter.R;
import com.example.wyj.smartbulter.entity.MyUser;
import com.example.wyj.smartbulter.utils.ShareUtils;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    // 注册按钮
    private Button btn_registered;
    private EditText et_name;
    private EditText et_password;
    private Button btnLogin;
    private CheckBox keep_password;
    private TextView tv_forget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        btn_registered = findViewById(R.id.btn_register);
        btn_registered.setOnClickListener(this);
        et_name = findViewById(R.id.et_name);
        et_password = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        keep_password = findViewById(R.id.keep_password);
        // 设置选中的状态
        boolean isCheck = ShareUtils.getBoolean(this, "keep pass", false); // 默认不记住密码
        keep_password.setChecked(isCheck);
        if (isCheck) {
            // 设置密码
            et_name.setText(ShareUtils.getString(this, "name", ""));
            et_password.setText(ShareUtils.getString(this, "password", ""));
        }

        tv_forget = findViewById(R.id.tv_forget);
        tv_forget.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                startActivity(new Intent(this, RegisteredActivity.class));
                break;
            case R.id.btnLogin:
                // 获取输入框的值
                String name = et_name.getText().toString().trim();
                String password = et_password.getText().toString();
                // 判断是否为空
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                    // 登录
                    final MyUser user = new MyUser();
                    user.setUsername(name);
                    user.setPassword(password);
                    user.login(new SaveListener<MyUser>() {
                        @Override
                        public void done(MyUser myUser, BmobException e) {
                            if (e == null) {
                                // 判断邮箱是否验证
                                if (user.getEmailVerified()) {
                                    // 跳转
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "请前往邮箱验证",
                                            Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "登录失败：" +
                                        e.getErrorCode(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_forget:
                startActivity(new Intent(this, ForgetPasswordActivity.class));
        }
    }

    // 假设输入用户名密码，但是不点击登录，直接退出了
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 保存状态
        ShareUtils.putBoolean(this, "keep pass", keep_password.isChecked());
        // 是否记住密码
        if (keep_password.isChecked()) {
            // 记住用户名和密码
            ShareUtils.putString(this, "name", et_name.getText().toString().trim());
            ShareUtils.putString(this, "password", et_password.getText().toString().trim());
        } else {
            ShareUtils.deleteShare(this, "name");
            ShareUtils.deleteShare(this, "password");
        }
    }
}
