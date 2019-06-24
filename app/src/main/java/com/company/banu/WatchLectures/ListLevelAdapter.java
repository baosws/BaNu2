package com.company.banu.WatchLectures;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.company.banu.R;
import com.company.banu.WatchLevels.ModelWatchLevels;

import java.util.List;


public class ListLevelAdapter extends BaseAdapter {
    List<ModelWatchLevels.Level> lectures;
    LayoutInflater layoutInflater;
    Context mContext;

    public ListLevelAdapter(Context context, List<ModelWatchLevels.Level> dataLecture)
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
    public Object getItem(int position) {
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

        ModelWatchLevels.Level lecture = lectures.get(position);
        viewHolder.tvLevel.setText(lecture.name);
        viewHolder.imgLevel.setImageResource(this.getMipmapResIdByName(lecture.image));

        return convertView;
    }

    public int getMipmapResIdByName(String resName)  {
        String pkgName = this.mContext.getPackageName();
        // Return 0 if not found
        int resID = this.mContext.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomGridView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    static class ViewHolder{
        ImageView imgLevel;
        TextView tvLevel;
    }
}
