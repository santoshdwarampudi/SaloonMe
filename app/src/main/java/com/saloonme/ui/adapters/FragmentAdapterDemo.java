package com.saloonme.ui.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.athbk.ultimatetablayout.IFTabAdapter;
import com.saloonme.R;
import com.saloonme.model.response.SaloonServiceResponseData;
import com.saloonme.ui.fragments.FragmentCategory;

import java.util.List;

public class FragmentAdapterDemo extends FragmentPagerAdapter implements IFTabAdapter {

    private List<SaloonServiceResponseData>
            saloonServiceResponseDataList;
    private String saloonId;

    public FragmentAdapterDemo(FragmentManager fm, List<SaloonServiceResponseData>
            saloonServiceResponseDataList,String saloonId) {
        super(fm);
        this.saloonServiceResponseDataList = saloonServiceResponseDataList;
        this.saloonId=saloonId;
    }


    @Override
    public Fragment getItem(int position) {
        return FragmentCategory.newInstance(saloonServiceResponseDataList.get(position).getId(),saloonId);
    }

    @Override
    public int getCount() {
        return saloonServiceResponseDataList != null ? saloonServiceResponseDataList.size() : 0;
    }

    @Override
    public String getTitle(int position) {
        return saloonServiceResponseDataList.get(position).getName();
    }

    @Override
    public int getIcon(int i) {
        return 0;
    }

   /* @Override
    public int getIcon(int position) {
        return R.drawable.tab_1_selected;
    }*/

    @Override
    public boolean isEnableBadge(int position) {
        if (position == 0) {
            return true;
        }
        return false;
    }
}