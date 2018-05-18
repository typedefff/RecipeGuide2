package com.example.bewika.recipeguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private List<Data> mDatas;
    private LayoutInflater mLayoutInflater;
    Context mContext;

//    private String iconBaseUrl = "http://";

    public MyAdapter(Context context, List<Data> aList) {
        mDatas = aList;
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }


    static class ViewHolder {
        TextView tvMenuType;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.row_layout,viewGroup,false);
            holder = new ViewHolder();
            holder.tvMenuType = (TextView)view.findViewById(R.id.txtype);

            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }
        String type = mDatas.get(position).getType();
        holder.tvMenuType.setText(type);

        return view;
    }
}


