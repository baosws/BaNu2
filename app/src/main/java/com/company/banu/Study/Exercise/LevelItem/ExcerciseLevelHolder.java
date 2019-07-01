package com.company.banu.Study.Exercise.LevelItem;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.company.banu.Quiz.ActivityQuiz;
import com.company.banu.Quiz.QuizLevel;
import com.company.banu.R;
import com.company.banu.WatchLectures.LectureItem.Lecture;

public class ExcerciseLevelHolder extends RecyclerView.ViewHolder implements ExcerciseLevelView {
    TextView tvName;
    TextView tvScore;
    ImageView imgState;
    CardView cvLevel;
    TextView tvNumQuiz;
    ExcerciseLevelPresenter presenter;
    View view;

    public ExcerciseLevelHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        presenter = new ExcerciseLevelPresenter(this);
    }

    public void getViews() {
        tvName = itemView.findViewById(R.id.tv_levelName);
        tvScore = itemView.findViewById(R.id.tv_score);
        tvNumQuiz = itemView.findViewById(R.id.tv_numQuiz);
        imgState = itemView.findViewById(R.id.img_state);
        cvLevel = itemView.findViewById(R.id.cv_levelExercise);
    }

    public void setOnClick(final boolean haveData) {
        cvLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClick(haveData);
            }
        });
    }

    @Override
    public void showNoExercise() {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Notification");
        builder.setMessage("No exercise! We will add later!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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
    public void updateScore(int passed, int total) {
        tvScore.setText(Integer.toString(passed));
        tvNumQuiz.setText("/" +Integer.toString(total));
    }

    @Override
    public void updateState(boolean passed) {
        if (passed)
        {
            Glide.with(view)
                    .load(R.drawable.fui_ic_check_circle_black_128dp)
                    .into(imgState);
        }
        else if (!passed)
        {
            Glide.with(view)
                    .load(R.drawable.ic_clear_red)
                    .into(imgState);
        }
    }
    @Override
    public void bind(ExcerciseLevel excerciseLevel) {
        presenter.setLevel(excerciseLevel);
    }
}
