package com.company.banu.Study.Exercise;

import android.content.Context;

import com.company.banu.R;
import com.company.banu.Study.Exercise.LevelItem.Level;

import java.util.ArrayList;
import java.util.List;

public class FragmentModel {
    List<Level> levelList;
    FragmentPresenter presenter;
    Context mContext;

    public FragmentModel(FragmentPresenter presenter, Context context)
    {
        this.presenter = presenter;
        this.mContext = context;
        initData();
    }

    void initData()
    {
        levelList = new ArrayList<com.company.banu.Study.Exercise.LevelItem.Level>();
        levelList.add(new com.company.banu.Study.Exercise.LevelItem.Level("Beginner", mContext.getResources().getColor(R.color.beginner)));
        levelList.add(new com.company.banu.Study.Exercise.LevelItem.Level("Intermediate", mContext.getResources().getColor(R.color.intermediate)));
        levelList.add(new com.company.banu.Study.Exercise.LevelItem.Level("Advanced", mContext.getResources().getColor(R.color.advanced)));
    }

    public List<Level> getLevelList()
    {
        return levelList;
    }
}
