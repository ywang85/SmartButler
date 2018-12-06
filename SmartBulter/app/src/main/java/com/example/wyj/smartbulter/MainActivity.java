package com.example.wyj.smartbulter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.wyj.smartbulter.fragment.ButlerFragment;
import com.example.wyj.smartbulter.fragment.GrilFragment;
import com.example.wyj.smartbulter.fragment.UserFragment;
import com.example.wyj.smartbulter.fragment.WechatFragment;
import com.example.wyj.smartbulter.ui.SettingActivity;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // TabLayout
    private TabLayout mTabLayout;
    // ViewPager
    private ViewPager mViewPager;
    // Title
    private List<String> mTitle;
    // Fragment
    private List<Fragment> mFragment;

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 去掉阴影
        getSupportActionBar().setElevation(0);

        initData();
        initView();

        
    }

    private void initData() {
        mTitle = new ArrayList<>();
        mTitle.add("服务管家");
        mTitle.add("微信精选");
        mTitle.add("美女社区");
        mTitle.add("个人中心");

        mFragment = new ArrayList<>();
        mFragment.add(new ButlerFragment());
        mFragment.add(new WechatFragment());
        mFragment.add(new GrilFragment());
        mFragment.add(new UserFragment());
    }

    @SuppressLint("RestrictedApi")
    private void initView() {
        mTabLayout = findViewById(R.id.mTabLayout);
        mViewPager = findViewById(R.id.mViewPager);
        fab = findViewById(R.id.fab_setting);
        fab.setOnClickListener(this);

        // 默认隐藏
        fab.setVisibility(View.GONE);

        // 预加载
        mViewPager.setOffscreenPageLimit(mFragment.size());

        // mViewPager 滑动监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                Log.i("Tag", "position: " + i);
                if (i == 0) {
                    fab.setVisibility(View.GONE);
                } else {
                    fab.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        // 设置适配器
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) { // 选中的item
                return mFragment.get(i);
            }

            @Override
            public int getCount() { // 返回item的个数
                return mFragment.size();
            }

            // 设置标题
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });

        // 绑定
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_setting:
                Intent intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                break;
        }
    }
}
