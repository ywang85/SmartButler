package com.example.wyj.smartbulter.ui;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wyj.smartbulter.R;
import com.example.wyj.smartbulter.utils.L;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {

    private ViewPager viewPager;

    // 容器
    private List<View> list = new ArrayList<>();
    private View view1, view2, view3;

    // 小圆点
    private ImageView point1, point2, point3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initView();

    }

    private void initView() {
        point1 = findViewById(R.id.point1);
        point2 = findViewById(R.id.point2);
        point3 = findViewById(R.id.point3);

        // 设置默认图片
        setPointImg(true, false, false);

        viewPager = findViewById(R.id.mViewPager);
        view1 = View.inflate(this, R.layout.pager_item_one, null);
        view2 = View.inflate(this, R.layout.pager_item_two, null);
        view3 = View.inflate(this, R.layout.pager_item_three, null);
        list.add(view1);
        list.add(view2);
        list.add(view3);

        // 设置适配器
        viewPager.setAdapter(new GuideAdapter());

        // 监听ViewPager滑动
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            // pager 切换
            @Override
            public void onPageSelected(int i) {
                L.i("position: " + i);
                switch (i) {
                    case 0:
                        setPointImg(true, false, false);
                        break;
                    case 1:
                        setPointImg(false, true, false);
                        break;
                    case 2:
                        setPointImg(false, false, true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(list.get(position));
//            super.destroyItem(container, position, object);
        }
    }

    // 设置小圆点的选中效果
    private void setPointImg(boolean isCheck1, boolean isCheck2, boolean isCheck3) {
        if (isCheck1) {
            point1.setBackgroundResource(R.drawable.point_on);
        } else {
            point1.setBackgroundResource(R.drawable.point_off);
        }

        if (isCheck2) {
            point2.setBackgroundResource(R.drawable.point_on);
        } else {
            point2.setBackgroundResource(R.drawable.point_off);
        }

        if (isCheck3) {
            point3.setBackgroundResource(R.drawable.point_on);
        } else {
            point3.setBackgroundResource(R.drawable.point_off);
        }
    }
}
