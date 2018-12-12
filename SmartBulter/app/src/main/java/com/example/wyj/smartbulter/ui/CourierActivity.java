package com.example.wyj.smartbulter.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.wyj.smartbulter.R;
import com.example.wyj.smartbulter.adapter.CompositionAdaper;
import com.example.wyj.smartbulter.entity.CompositionData;
import com.example.wyj.smartbulter.utils.StaticClass;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CourierActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_get_courier;
    private ListView mListView;
    private EditText et_id;

    private List<CompositionData> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courier);
        initView();
    }

    private void initView() {
        btn_get_courier = findViewById(R.id.btn_get_courier);
        btn_get_courier.setOnClickListener(this);
        mListView = findViewById(R.id.mListView);
        et_id = findViewById(R.id.et_id);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_courier:
                // 1. 获取输入框的内容
                String id = et_id.getText().toString().trim();

                // 拼接url
                String url = "http://zuowen.api.juhe.cn/zuowen/typeList?key=" + StaticClass.COURIER_KEY
                        + "&id=" + id;
                // 2. 判断是否为空
                if (!TextUtils.isEmpty(id)) {
                    // 3. 拿到数据去请求数据
                    RxVolley.get(url, new HttpCallback() {
                        @Override
                        public void onSuccess(String t) {
//                            Toast.makeText(CourierActivity.this, t, Toast.LENGTH_SHORT).show();
//                            L.i("Json: " + t);
                            // 4. 解析json
                            parsingJson(t);
                        }
                    });
                } else {
                    Toast.makeText(CourierActivity.this, "输入框不能为空",
                            Toast.LENGTH_SHORT).show();
                }
                break;

                // 5. listview适配器
                // 6. 实体类 item
                // 7. 设置数据/显示效果


        }
    }

    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);
                CompositionData data = new CompositionData();
                data.setId(json.getString("id"));
                Log.i("book", json.getString("id"));
                Log.i("book", json.getString("name"));
                data.setName(json.getString("name"));
                mList.add(data);
            }
            CompositionAdaper adaper = new CompositionAdaper(this, mList);
            mListView.setAdapter(adaper);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
