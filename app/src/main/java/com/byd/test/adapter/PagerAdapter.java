package com.byd.test.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.byd.test.fragments.FixturesFragment;
import com.byd.test.fragments.ResultsFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int nNumbOfTabs;

    public PagerAdapter(FragmentManager fm,int numOfTabs) {
        super(fm);
        this.nNumbOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FixturesFragment tab1 =  new FixturesFragment();
                return tab1;
            case 1:
                ResultsFragment tab2 =  new ResultsFragment();
                return  tab2;

                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return this.nNumbOfTabs;
    }
}
