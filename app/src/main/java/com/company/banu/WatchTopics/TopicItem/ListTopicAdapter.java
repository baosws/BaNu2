package com.company.banu.WatchTopics.TopicItem;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.company.banu.R;

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
    public Topic getItem(int position) {
        return topicList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("btag", "getView: ");
        TopicViewHolder viewHolder;
        convertView = layoutInflater.inflate(R.layout.grid_detail_level_item_layout, null);
        viewHolder = new TopicViewHolder(convertView, topicList.get(position));
        convertView.setTag(viewHolder);

        return convertView;
    }
}
