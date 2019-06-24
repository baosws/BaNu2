package com.company.banu.WatchLevels;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.company.banu.R;
import com.company.banu.WatchLectures.ListLevelAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


import static android.widget.Toast.LENGTH_LONG;

public class ViewWatchLevels extends AppCompatActivity {
    static int layoutId = R.layout.activity_watch_levels;
    PresenterWatchLevels presenterWatchLevels;
    GridView gridView_listLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        getView();
        presenterWatchLevels = new PresenterWatchLevels(this);

        loadGridviewListLevel();
    }

    void getView()
    {
        gridView_listLevel = findViewById(R.id.gv_listLevels);
    }

    void loadGridviewListLevel()
    {
        final List<ModelWatchLevels.Level> levels = new ArrayList<>();
        levels.add(new ModelWatchLevels.Level("Grade 1", "level1"));
        levels.add(new ModelWatchLevels.Level("Grade 2", "level2"));
        levels.add(new ModelWatchLevels.Level("Grade 3", "level3"));

        gridView_listLevel.setAdapter(new ListLevelAdapter(this, levels));

        gridView_listLevel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ModelWatchLevels.Level selectedItem = (ModelWatchLevels.Level) gridView_listLevel.getItemAtPosition(position);
                Toast.makeText(ViewWatchLevels.this, "Selected :"
                        + selectedItem.levelName, Toast.LENGTH_LONG).show();
            }
        });
    }

}
