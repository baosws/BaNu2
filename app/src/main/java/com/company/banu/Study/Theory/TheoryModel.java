package com.company.banu.Study.Theory;

import com.company.banu.CallBack;
import com.company.banu.WatchLectures.LectureItem.Lecture;
import com.company.banu.WatchLevels.MediaResource;

class TheoryModel {
    Lecture lecture;
    TheoryModel(Lecture lecture) {
        this.lecture = lecture;
    }

    public void getResource(CallBack<MediaResource> cb) {
        lecture.getResource(cb);
    }

    public void getDescription(CallBack<String> cb) {
        lecture.getDescription(cb);
    }
}
