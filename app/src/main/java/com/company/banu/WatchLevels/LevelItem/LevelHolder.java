package com.company.banu.WatchLevels.LevelItem;

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.company.banu.CallBack;
import com.company.banu.R;
import com.company.banu.WatchTopics.ActivityWatchTopics;

public class LevelHolder implements LevelView {
    View view;
    ImageView imgLevel;
    TextView tvLevel;
    LevelPresenter levelPresenter;
    LevelHolder(View view, Level level) {
        assert view != null;
        assert level != null;
        this.view = view;
        levelPresenter = new LevelPresenter(this, level);
    }
    public void getViews() {
        Log.d("btag", "getViews: ");
        imgLevel = view.findViewById(R.id.img_item_level);
        imgLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(view.getContext(), ActivityWatchTopics.class);
                levelPresenter.getLevelId(new CallBack<String>() {
                    @Override
                    public void call(String data) {
                        Log.d("btag", "call: levelId " + data);
                        intent.putExtra("levelId", data);
                        view.getContext().startActivity(intent);
                    }
                });
            }
        });
        tvLevel = view.findViewById(R.id.tv_item_levelName);
    }
    public void setImage(Bitmap bitmap) {
        Glide.with(view.getContext())
                .load(bitmap)
                .into(imgLevel);
//        imgLevel.setImageBitmap(bitmap);
    }
    public void setName(String name) {
        tvLevel.setText(name);
    }
}
