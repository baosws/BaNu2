package com.company.banu.DetailLevel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.company.banu.R;

import java.util.List;

import Universe.LoadImageResource;

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
        ViewHolder viewHolder;
        if (convertView == null)
        {
            convertView = (View) layoutInflater.inflate(R.layout.grid_detail_level_item_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.imgTopic = convertView.findViewById(R.id.imv_topic);
            viewHolder.tvTopic = convertView.findViewById(R.id.tv_topic);
            viewHolder.rbTopic = convertView.findViewById(R.id.rb_percentLearned);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Topic topic = topicList.get(position);
        viewHolder.tvTopic.setText(topic.name);
        int resImageID = LoadImageResource.getMipmapResIdByName(this.mContext, topic.image);
        viewHolder.imgTopic.setImageResource(resImageID);
        viewHolder.rbTopic.setRating(topic.percentLearned);
        return convertView;
    }

    static class ViewHolder{
        ImageView imgTopic;
        TextView tvTopic;
        RatingBar rbTopic;
    }
}
