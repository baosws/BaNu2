package com.company.banu.Study;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.company.banu.CallBack;
import com.company.banu.Study.Exercise.FragmentExercise;
import com.company.banu.Study.Theory.FragmentTheory;
import com.company.banu.WatchLectures.LectureItem.Lecture;

import java.util.ArrayList;

public class TabAdapter extends FragmentStatePagerAdapter {

    public ArrayList<StudyFragment> fragments;

    public TabAdapter(FragmentManager fm, final Lecture lecture) {
        super(fm);
        fragments = new ArrayList<>();
        fragments.add(new FragmentTheory());
        fragments.add(new FragmentExercise());

        for (final StudyFragment fragment: fragments) {
            final Bundle args = new Bundle();
            lecture.getId(new CallBack<String>() {
                @Override
                public void call(String data) {
                    args.putString("lectureId", data);
                    fragment.setArguments(args);
                }
            });

        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getName();
    }
}
