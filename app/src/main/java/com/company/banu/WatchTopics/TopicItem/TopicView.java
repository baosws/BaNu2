package com.company.banu.WatchTopics.TopicItem;

import android.graphics.Bitmap;

public interface TopicView {
    void getViews();
    void setName(String name);
    void setImage(Bitmap bitmap);
    void setRating(float rating);
}
