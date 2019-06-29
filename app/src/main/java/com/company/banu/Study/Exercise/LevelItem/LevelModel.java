package com.company.banu.Study.Exercise.LevelItem;


public class LevelModel {

    LevelPresenter presenter;
    ExcerciseLevel excerciseLevel;

    public LevelModel(LevelPresenter levelPresenter)
    {
        this.presenter = levelPresenter;
    }

    public void setExcerciseLevel(ExcerciseLevel excerciseLevel){
        this.excerciseLevel = excerciseLevel;
    }

    public String getName(){
        return excerciseLevel.name;
    }

    public int getColor()
    {
        return excerciseLevel.color;
    }
}
