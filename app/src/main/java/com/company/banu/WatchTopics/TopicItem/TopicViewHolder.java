package com.company.banu.WatchTopics.TopicItem;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.company.banu.Backend;
import com.company.banu.CallBack;
import com.company.banu.R;
import com.company.banu.WatchLectures.ActivityWatchLectures;

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
        imgTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(view.getContext(), ActivityWatchLectures.class);
                topicViewPresenter.getTopicId(new CallBack<String>() {
                    @Override
                    public void call(String data) {
                        intent.putExtra("topicId", data);
                        view.getContext().startActivity(intent);
                    }
                });
            }
        });
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
