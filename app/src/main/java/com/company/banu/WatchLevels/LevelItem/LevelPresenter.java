package com.company.banu.WatchLevels.LevelItem;

public class LevelPresenter {
    LevelView view;
    LevelModel levelModel;
    public LevelPresenter(LevelView view, Level level) {
        assert view != null;
        assert level != null;
        this.view = view;
        levelModel = new LevelModel(this, level);
        view.getViews();
        init();
    }

    private void init() {
        view.setName(levelModel.getName());
        view.setImage(levelModel.getImage());
    }

    void invalidate() {
        init();
    }
}
