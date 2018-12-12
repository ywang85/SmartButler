package com.example.wyj.smartbulter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wyj.smartbulter.R;
import com.example.wyj.smartbulter.entity.CompositionData;

import java.util.List;

// 作文查询
public class CompositionAdaper extends BaseAdapter {
    private Context mcontext;
    private List<CompositionData> mList;
    // 布局加载器
    private LayoutInflater inflater;
    private CompositionData compositionData;


    public CompositionAdaper(Context mcontext, List<CompositionData> mList) {
        this.mcontext = mcontext;
        this.mList = mList;
        // 获取系统服务
        inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) { // 第一次假装
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.layout_composition_item, null);
            viewHolder.tv_name = convertView.findViewById(R.id.tv_name);
            viewHolder.tv_id = convertView.findViewById(R.id.tv_id);
            // 设置缓存
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 设置数据
        compositionData = mList.get(position);
        viewHolder.tv_id.setText(compositionData.getId());
        viewHolder.tv_name.setText(compositionData.getName());

        return convertView;
    }

    class ViewHolder {
        private TextView tv_name;
        private TextView tv_id;
    }
}
