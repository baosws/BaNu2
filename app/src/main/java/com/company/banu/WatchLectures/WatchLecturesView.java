package com.company.banu.WatchLectures;

import java.util.List;

public interface WatchLecturesView {
    void getViews();
    void initUI();
    void showLectures(List<Lecture> letures);
}
