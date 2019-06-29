package com.company.banu.Study.Exercise.LevelItem;

public class LevelPresenter {
    LevelModel model;
    LevelView view;

    public LevelPresenter(LevelView levelView)
    {
        this.view = levelView;
        this.model = new LevelModel(this);
    }

    public void setLevel(ExcerciseLevel excerciseLevel)
    {
        model.setExcerciseLevel(excerciseLevel);
    }

    public void updateUI()
    {
        view.setNameandColor(model.getName(), model.getColor());
    }
}
