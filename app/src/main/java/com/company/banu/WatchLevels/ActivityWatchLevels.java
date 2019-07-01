package com.company.banu.WatchLevels;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.company.banu.CallBack;
import com.company.banu.R;
import com.company.banu.ShowProfile.ShowProfileActivity;
import com.company.banu.ShowProfile.ViewShowProfile;
import com.company.banu.WatchLevels.LevelItem.Level;
import com.company.banu.WatchLevels.LevelItem.ListLevelAdapter;
import com.github.abdularis.civ.AvatarImageView;

import java.util.ArrayList;
import java.util.List;

public class ActivityWatchLevels extends AppCompatActivity implements WatchLevelView {
    static int layoutId = R.layout.activity_watch_levels;
    ListLevelAdapter listLevelAdapter;
    PresenterWatchLevels presenterWatchLevels;
    GridView gridView_listLevel;
    AvatarImageView avatarImageView;
    TextView tvScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        presenterWatchLevels = new PresenterWatchLevels(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (listLevelAdapter != null) {
            listLevelAdapter.notifyDataSetChanged();
        }
    }

    public void getViews()
    {
        tvScore = findViewById(R.id.tv_score);
        gridView_listLevel = findViewById(R.id.gv_listLevels);
        avatarImageView = findViewById(R.id.imv_avatar);
    }

    public void loadGridviewListLevel(List<Level> levels)
    {
        listLevelAdapter = new ListLevelAdapter(this, levels);
        for (Level level: levels) {
            level.getAny(new CallBack<Level>() {
                @Override
                public void call(Level data) {
                    listLevelAdapter.notifyDataSetChanged();
                    gridView_listLevel.invalidate();
                }
            });
        }
        gridView_listLevel.setAdapter(listLevelAdapter);

        avatarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ShowProfileActivity.class));
            }
        });
    }

    public void setScore(Integer score) {
        tvScore.setText(score.toString());
    }

    public void setAvatar(Bitmap avatar) {
        avatarImageView.setImageBitmap(avatar);
    }
}
