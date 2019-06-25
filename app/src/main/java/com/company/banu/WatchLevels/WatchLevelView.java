package com.company.banu.WatchLevels;

import android.graphics.Bitmap;

import com.company.banu.WatchLevels.LevelItem.Level;

import java.util.List;

public interface WatchLevelView {
    void getViews();
    void loadGridviewListLevel(List<Level> levels);
    void setAvatar(Bitmap avatar);
    void setScore(Integer score);
}
