package com.company.banu.Study.Exercise.LevelItem;

import com.company.banu.Study.Exercise.LevelItem.Level;

public interface LevelView {
    void bind(Level level);
    void setNameandColor(String name, int color);
    void updateScore(int score);
    void updateState(boolean passed);
}
