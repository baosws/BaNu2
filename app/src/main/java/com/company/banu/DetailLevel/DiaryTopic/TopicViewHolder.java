package com.company.banu.DetailLevel.DiaryTopic;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.company.banu.R;

public class TopicViewHolder implements TopicView {
    ImageView imgTopic;
    TextView tvTopic;
    RatingBar rbTopic;
    View view;
    TopicViewPresenter topicViewPresenter;

    public TopicViewHolder(View view, Topic topic) {
        this.view = view;
        topicViewPresenter = new TopicViewPresenter(this, topic);
    }

    @Override
    public void getViews() {
        imgTopic = view.findViewById(R.id.imv_topic);
        tvTopic = view.findViewById(R.id.tv_topic);
        rbTopic = view.findViewById(R.id.rb_percentLearned);
    }

    @Override
    public void setName(String name) {
        tvTopic.setText(name);
    }

    @Override
    public void setImage(Bitmap bitmap) {
        imgTopic.setImageBitmap(bitmap);
    }

    @Override
    public void setRating(float rating) {
        rbTopic.setRating(rating);
    }
}
