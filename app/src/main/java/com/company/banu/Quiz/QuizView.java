package com.company.banu.Quiz;

import android.graphics.Bitmap;

public interface QuizView {
    void getViews();
    void showDialog();
    void setImage(Bitmap bitmap);
    void getData();
    void updateScore(String score);
    void clearPainter();
}