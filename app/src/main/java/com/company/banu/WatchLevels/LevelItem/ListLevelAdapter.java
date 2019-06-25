package com.company.banu.WatchLevels.LevelItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.company.banu.R;

import java.util.List;


public class ListLevelAdapter extends BaseAdapter {
    List<Level> levels;
    LayoutInflater layoutInflater;
    Context mContext;

    public ListLevelAdapter(Context context, List<Level> dataLevel)
    {
        this.mContext = context;
        this.levels = dataLevel;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return levels.size();
    }

    @Override
    public Level getItem(int position) {
        return levels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.grid_level_item_layout, null);
        LevelHolder viewHolder = new LevelHolder(convertView, levels.get(position));
        convertView.setTag(viewHolder);

        return convertView;
    }
}
