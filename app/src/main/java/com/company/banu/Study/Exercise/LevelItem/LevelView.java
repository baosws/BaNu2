package com.company.banu.Study.Exercise.LevelItem;

public interface LevelView {
    void bind(ExcerciseLevel excerciseLevel);
    void setNameandColor(String name, int color);
    void updateScore(int score);
    void updateState(boolean passed);
}
