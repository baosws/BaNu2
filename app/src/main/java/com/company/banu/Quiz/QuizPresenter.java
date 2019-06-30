package com.company.banu.Quiz;

import android.app.Dialog;
import android.graphics.Bitmap;

import com.company.banu.CallBack;
import com.company.banu.Notifier.Notifier;
import com.company.banu.R;

import java.util.ArrayList;

public class QuizPresenter {
    QuizView view;
    QuizModel model;
    int current = 0;

    public QuizPresenter(QuizView view) {
        this.view = view;
        model = new QuizModel();
    }

    public void init() {
        view.getData();
        view.getViews();
    }

    public void onPause() {
        view.showDialog();
    }

    public void setExcercises(ArrayList<Excercise> excercises) {
        model.setExcercises(excercises);
    }
}

