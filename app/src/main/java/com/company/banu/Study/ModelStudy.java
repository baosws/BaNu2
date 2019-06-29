package com.company.banu.Study;

import com.company.banu.CallBack;
import com.company.banu.WatchLectures.LectureItem.Lecture;

public class ModelStudy {
    Lecture lecture;
    public ModelStudy(Lecture lecture) {
        this.lecture = lecture;
    }

    public void getLectureName(CallBack<String> cb) {
        lecture.getName(cb);
    }

    public Lecture getLecture() {
        return lecture;
    }
}
