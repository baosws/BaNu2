package com.company.banu.Study;

import com.company.banu.WatchLectures.LectureItem.Lecture;

public interface StudyView {
    void initUI(Lecture lecture);
    void setTitle(String title);
}
