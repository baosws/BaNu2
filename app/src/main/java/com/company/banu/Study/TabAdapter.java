package com.company.banu.Study;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.company.banu.Study.Exercise.FragmentExercise;
import com.company.banu.Study.Theory.FragmentTheory;

public class TabAdapter extends FragmentStatePagerAdapter {

    private String listTab[] = {"Theory", "Exercise"};
    FragmentTheory fragmentTheory;
    FragmentExercise fragmentExercise;

    public TabAdapter(FragmentManager fm) {
        super(fm);
        fragmentExercise = new FragmentExercise();
        fragmentTheory = new FragmentTheory();
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
        {
            return fragmentTheory;
        }
        else
            if (position == 1)
            {
                return fragmentExercise;
            }

            return null;
    }

    @Override
    public int getCount() {
        return listTab.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTab[position];
    }
}
