package com.company.banu.Study.Exercise.LevelItem;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.company.banu.R;

public class LevelHolder extends RecyclerView.ViewHolder implements LevelView {
    TextView tvName;
    TextView tvScore;
    ImageView imgState;
    LevelPresenter presenter;

    public LevelHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tv_levelName);
        tvScore = itemView.findViewById(R.id.tv_scoreExercise);
        imgState = itemView.findViewById(R.id.img_state);
        presenter = new LevelPresenter(this);
    }

    @Override
    public void bind(Level level) {
        presenter.setLevel(level);
        presenter.updateUI();
    }

    @Override
    public void setNameandColor(String name, int color) {
        tvName.setText(name);
        tvName.setBackgroundColor(color);
    }

    @Override
    public void updateScore(int score) {

    }

    @Override
    public void updateState(boolean passed) {

    }
}
