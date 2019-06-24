package com.company.banu.WatchLevels;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.company.banu.R;
import com.company.banu.WatchLectures.Lecture;

import java.util.List;

import Universe.LoadImageResource;


public class ListLevelAdapter extends BaseAdapter {
    List<Level> lectures;
    LayoutInflater layoutInflater;
    Context mContext;

    public ListLevelAdapter(Context context, List<Level> dataLecture)
    {
        this.mContext = context;
        this.lectures = dataLecture;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lectures.size();
    }

    @Override
    public Level getItem(int position) {
        return lectures.get(position);
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
            convertView = layoutInflater.inflate(R.layout.grid_level_item_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.imgLevel = convertView.findViewById(R.id.img_item_level);
            viewHolder.tvLevel = convertView.findViewById(R.id.tv_item_levelName);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Level lecture = lectures.get(position);
        viewHolder.tvLevel.setText(lecture.name);
        viewHolder.imgLevel.setImageResource(LoadImageResource.getMipmapResIdByName(this.mContext, lecture.image));

        return convertView;
    }

    static class ViewHolder{
        ImageView imgLevel;
        TextView tvLevel;
    }
}
