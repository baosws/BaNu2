package com.company.banu.SolveProblem;

import android.app.Activity;

public class PresenterSolveProblem {
    Activity activity;
    ModelSolveProblem modelSolveProblem;
    public PresenterSolveProblem(Activity activity) {
        this.activity = activity;
        modelSolveProblem = new ModelSolveProblem();
    }

    public void bindLevelsToView() {

    }
}
