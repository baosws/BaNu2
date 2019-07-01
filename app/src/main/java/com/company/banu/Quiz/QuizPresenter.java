package com.company.banu.Quiz;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.company.banu.CallBack;
import com.company.banu.Classifier;
import com.company.banu.Result;

import java.util.ArrayList;

public class QuizPresenter {
    QuizView view;
    QuizModel model;
    int current = -1;

    public QuizPresenter(QuizView view, Classifier classifier) {
        this.view = view;
        model = new QuizModel(classifier);
    }

    public void init() {
        view.getViews();
    }

    public void onPause() {
        view.showDialog();
    }

    public void setExcercises(ArrayList<Excercise> excercises) {
        model.setExcercises(excercises);
        current = -1;
        view.setNumQuiz(excercises.size());
        nextQuiz();
    }

    private void nextQuiz() {
        current++;
        Log.d("btag", String.format("QuizPresenter:nextQuiz: "));
        model.getExcercise(current, new CallBack<Excercise>() {
            @Override
            public void call(Excercise data) {
                if (data != null) {
                    data.getImage(new CallBack<Bitmap>() {
                        @Override
                        public void call(Bitmap data) {
                            view.setImage(data);
                        }
                    });
                }
            }
        });
    }

    private void updateScore()
    {
        view.updateScore();
    }

    public void check(Bitmap bitmap) {
        Result result = model.check(bitmap);
        final String out = String.valueOf(result.getNumber());
        Log.d("btag", String.format("QuizPresenter:check: out = %s, conf = %f, %d", out, result.getProbability(), result.getTimeCost()));

        model.getExcercise(current, new CallBack<Excercise>() {
            @Override
            public void call(Excercise data) {
                if (data != null) {
                    model.check(out, data, new CallBack<Boolean>() {
                        @Override
                        public void call(Boolean data) {
                            Log.d("btag", String.format("QuizPresenter:call: " + data));
                            Toast.makeText((AppCompatActivity) view, data ? "Correct!!!" : "Incorrect!!!", Toast.LENGTH_SHORT).show();
                            view.clearPainter();
                            if (data) {
                                updateScore();
                            }
                            nextQuiz();
                        }
                    });

                }
            }
        });
    }
}

