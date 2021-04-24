package com.saloonme.ui.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.athbk.ultimatetablayout.IFTabAdapter;
import com.saloonme.R;
import com.saloonme.ui.fragments.FragmentCategory;

public class FragmentAdapterDemo extends FragmentPagerAdapter implements IFTabAdapter {


    public FragmentAdapterDemo(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentCategory.newInstance();
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public String getTitle(int position) {
        return "Hair";
    }

    @Override
    public int getIcon(int position) {
        return R.drawable.tab_1_selected;
    }

    @Override
    public boolean isEnableBadge(int position) {
        if (position == 0) {
            return true;
        }
        return false;
    }
}