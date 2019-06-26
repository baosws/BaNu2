package com.company.banu.WatchLectures;

import com.company.banu.WatchLectures.LectureItem.Lecture;

import java.util.List;

public interface WatchLecturesView {
    void getViews();
    void initUI();
    void setTitle(String title);
    void showLectures(List<Lecture> letures);
}
