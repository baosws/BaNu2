package com.company.banu.Study.Exercise.LevelItem;


public class LevelModel {

    LevelPresenter presenter;
    Level level;

    public LevelModel(LevelPresenter levelPresenter)
    {
        this.presenter = levelPresenter;
    }

    public void setLevel(Level level){
        this.level = level;
    }

    public String getName(){
        return level.name;
    }

    public int getColor()
    {
        return level.color;
    }
}
