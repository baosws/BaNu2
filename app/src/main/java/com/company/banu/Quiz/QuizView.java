package com.company.banu.Quiz;

import android.graphics.Bitmap;

public interface QuizView {
    void getViews();
    void showDialogPause();
    void showDialogDone();
    void setImage(Bitmap bitmap);
    void getData();
    void updateScore();
    void clearPainter();
    void setNumQuiz(int size);
    void resetScore();
}