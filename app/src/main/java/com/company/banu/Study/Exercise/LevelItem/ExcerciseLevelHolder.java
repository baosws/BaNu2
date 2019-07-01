package com.company.banu.Study.Exercise.LevelItem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.company.banu.Quiz.ActivityQuiz;
import com.company.banu.Quiz.QuizLevel;
import com.company.banu.R;
import com.company.banu.WatchLectures.LectureItem.Lecture;

public class ExcerciseLevelHolder extends RecyclerView.ViewHolder implements ExcerciseLevelView {
    TextView tvName;
    TextView tvScore;
    ImageView imgState;
    CardView cvLevel;
    ExcerciseLevelPresenter presenter;

    public ExcerciseLevelHolder(@NonNull View itemView) {
        super(itemView);
        presenter = new ExcerciseLevelPresenter(this);
    }

    public void getViews() {
        tvName = itemView.findViewById(R.id.tv_levelName);
        tvScore = itemView.findViewById(R.id.tv_scoreExercise);
        imgState = itemView.findViewById(R.id.img_state);
        cvLevel = itemView.findViewById(R.id.cv_levelExercise);
        cvLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClick();
            }
        });
    }

    public void startQuiz(String lectureId, QuizLevel level) {
        Intent intent = new Intent(itemView.getContext(), ActivityQuiz.class);
        intent.putExtra("lectureId", lectureId);
        intent.putExtra("level", level.toString());

        itemView.getContext().startActivity(intent);
    }
    @Override
    public void setName(String name) {
        tvName.setText(name);
    }

    @Override
    public void setColor(int color) {
        tvName.setBackgroundColor(color);
    }

    @Override
    public void updateScore(int score) {

    }

    @Override
    public void updateState(boolean passed) {

    }

    @Override
    public void bind(ExcerciseLevel excerciseLevel) {
        presenter.setLevel(excerciseLevel);
    }
}
