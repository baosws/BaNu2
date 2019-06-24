package com.company.banu.WatchLevels;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.company.banu.R;

import java.util.ArrayList;
import java.util.List;

public class ViewWatchLevels extends AppCompatActivity {
    static int layoutId = R.layout.activity_watch_levels;
    PresenterWatchLevels presenterWatchLevels;
    GridView gridView_listLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        getView();
        try {
            presenterWatchLevels = new PresenterWatchLevels(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        loadGridviewListLevel();
    }

    void getView()
    {
        gridView_listLevel = findViewById(R.id.gv_listLevels);
    }

    void loadGridviewListLevel()
    {
        final List<Level> levels = new ArrayList<>();
        levels.add(new Level("Grade 1", "level1"));
        levels.add(new Level("Grade 2", "level2"));
        levels.add(new Level("Grade 3", "level3"));

        gridView_listLevel.setAdapter(new ListLevelAdapter(this, levels));

        gridView_listLevel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Level selectedItem = (Level) gridView_listLevel.getItemAtPosition(position);
                Toast.makeText(ViewWatchLevels.this, "Selected :"
                        + selectedItem.name, Toast.LENGTH_LONG).show();
            }
        });
    }

}
