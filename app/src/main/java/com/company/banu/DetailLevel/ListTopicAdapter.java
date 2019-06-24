package com.company.banu.DetailLevel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class ListTopicAdapter extends BaseAdapter {
    List<Topic> topicList;
    LayoutInflater layoutInflater;
    Context mContext;

    public ListTopicAdapter(Context context, List<Topic> dataTopic){
        this.mContext = context;
        this.topicList = dataTopic;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return topicList.size();
    }

    @Override
    public Object getItem(int position) {
        return topicList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return null;
    }

    static class ViewHolder{
        ImageView imgTopic;
        TextView tvTopic;
        RatingBar rbTopic;
    }
}
