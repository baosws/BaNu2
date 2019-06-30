package com.company.banu.Study.Exercise.LevelItem;

import com.company.banu.Quiz.QuizLevel;

public interface ExcerciseLevelView {
    void bind(ExcerciseLevel excerciseLevel);
    void setName(String name);
    void setColor(int color);
    void updateScore(int score);
    void updateState(boolean passed);
    void getViews();
    void startQuiz(String lectureId, QuizLevel level);
}
