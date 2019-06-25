package com.company.banu.DetailLevel;

import android.graphics.Bitmap;

import com.company.banu.CallBack;
import com.company.banu.DetailLevel.DiaryTopic.Topic;

import java.util.ArrayList;

public class PresenterDetailLevel {
    private final ViewDetailLevel activity;
    ModelDetailLevel modelDetailLevel;

    PresenterDetailLevel(final ViewDetailLevel activity) {
        this.activity = activity;
        modelDetailLevel = new ModelDetailLevel();
        activity.getViews();
        init();
    }

    private void invalidateTopic(int position) {
        activity.invalidateTopic();
    }

    private void init() {
        modelDetailLevel.getTopics(new CallBack<ArrayList<Topic>>() {
            @Override
            public void call(ArrayList<Topic> data) {
                activity.loadGridViewListTopics(data);
            }
        }, new CallBack<Integer>() {
            @Override
            public void call(Integer data) {
                invalidateTopic(data);
            }
        });

        modelDetailLevel.getAvatar(new CallBack<Bitmap>() {
            @Override
            public void call(Bitmap data) {
                activity.loadAvatar(data);
            }
        });
    }
}
