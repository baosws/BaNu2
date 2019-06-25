package com.company.banu.WatchLevels.LevelItem;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.company.banu.R;

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
        imgLevel = view.findViewById(R.id.img_item_level);
        tvLevel = view.findViewById(R.id.tv_item_levelName);
    }
    public void setImage(Bitmap bitmap) {
        imgLevel.setImageBitmap(bitmap);
    }
    public void setName(String name) {
        tvLevel.setText(name);
    }
}
