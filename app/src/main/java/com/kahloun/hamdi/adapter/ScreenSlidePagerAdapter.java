package com.kahloun.hamdi.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class ScreenSlidePagerAdapter  extends FragmentStatePagerAdapter {
    private List<Fragment> myFragments;
    public ScreenSlidePagerAdapter(FragmentManager fm,List<Fragment> myFragments) {
        super(fm);
        this.myFragments = myFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return myFragments.get(position);
    }

    @Override
    public int getCount() {
        return myFragments.size();
    }

}
