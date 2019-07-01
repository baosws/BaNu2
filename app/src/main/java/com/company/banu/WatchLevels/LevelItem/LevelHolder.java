package com.company.banu.WatchLevels.LevelItem;

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.company.banu.CallBack;
import com.company.banu.R;
import com.company.banu.WatchTopics.ActivityWatchTopics;

import Universe.GrayscaleTransformation;

public class LevelHolder implements LevelView {
    private static final RequestOptions GRAYSCALE = new RequestOptions().transform(new GrayscaleTransformation());
    View view;
    ImageView imgLevel;
    TextView tvLevel;
    LevelPresenter levelPresenter;
    Bitmap bitmapLevel;

    LevelHolder(View view, Level level) {
        assert view != null;
        assert level != null;
        this.view = view;
        levelPresenter = new LevelPresenter(this, level);
    }
    public void getViews() {
        Log.d("btag", "getViews: ");
        imgLevel = view.findViewById(R.id.img_item_level);
        tvLevel = view.findViewById(R.id.tv_item_levelName);
    }
    public void setImage(Bitmap bitmap) {
        bitmapLevel = bitmap.copy(null, false);
        Glide.with(view.getContext())
                .applyDefaultRequestOptions(GRAYSCALE)
                .load(bitmap)
                .into(imgLevel);
    }

    @Override
    public void updatePassed(boolean passed) {
        if (passed)
        {
//            Glide.with(view.getContext())
//                    .load(bitmapLevel)
//                    .into(imgLevel);

            imgLevel.setImageBitmap(bitmapLevel);

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
        }
    }

    public void setName(String name) {
        tvLevel.setText(name);
    }
}
