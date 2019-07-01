package com.company.banu.WatchLevels.LevelItem;

import android.graphics.Bitmap;

public interface LevelView {
    void getViews();
    void setName(String name);
    void setImage(Bitmap bitmap);
    void updatePassed(boolean passed);
}
