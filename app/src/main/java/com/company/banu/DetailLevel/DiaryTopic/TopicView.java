package com.company.banu.DetailLevel.DiaryTopic;

import android.graphics.Bitmap;

public interface TopicView {
    void getViews();
    void setName(String name);
    void setImage(Bitmap bitmap);
    void setRating(float rating);
}
