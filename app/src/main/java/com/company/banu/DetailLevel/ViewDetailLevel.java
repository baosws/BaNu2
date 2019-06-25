package com.company.banu.DetailLevel;

import android.graphics.Bitmap;

import com.company.banu.DetailLevel.DiaryTopic.Topic;

import java.util.List;

public interface ViewDetailLevel {
    void getViews();
    void loadGridViewListTopics(List<Topic> topics);
    void loadAvatar(Bitmap bitmap);
    void invalidateTopic();
}
