package com.company.banu.WatchLectures.LectureItem;

public interface LectureView {
    void getViews();
    public void bind(Lecture lecture);
    void setLectureName(String name);
    void setLectureId(String id);
    void setPercent(float rating);
}
