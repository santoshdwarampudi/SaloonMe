package com.saloonme.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;
import com.saloonme.R;
import com.saloonme.interfaces.APIConstants;
import com.saloonme.interfaces.IProfileView;
import com.saloonme.model.response.ProfileResponse;
import com.saloonme.model.response.ProfileResponseData;
import com.saloonme.network.APIClient;
import com.saloonme.presenters.ProfilePresenter;
import com.saloonme.ui.adapters.HistoryAdapter;
import com.saloonme.util.PrefUtils;

import java.util.List;

import butterknife.BindView;


public class ProfileFragment extends BaseFragment implements HistoryAdapter.ItemListener, IProfileView {

    private HistoryAdapter historyAdapter;
    private ProfilePresenter profilePresenter;
    @BindView(R.id.bookingsTabs)
    TabLayout bookingsTabs;
    @BindView(R.id.rv_bookings)
    RecyclerView rv_bookings;
    private View view;
    @BindView(R.id.iv_profile)
    ImageView iv_profile;
    @BindView(R.id.tv_userName)
    TextView tv_userName;
    @BindView(R.id.tv_dob)
    TextView tv_dob;
    @BindView(R.id.tv_mobile)
    TextView tv_mobile;
    @BindView(R.id.tv_email)
    TextView tv_email;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        profilePresenter = new ProfilePresenter(APIClient.getAPIService(), this);
        setUpTabs();
        setUpRecyclerview(false);
        profilePresenter.getProfileDetails(PrefUtils.getInstance().getUserId() ,
                PrefUtils.getInstance().geToken());
        return view;
    }

    private void setUpRecyclerview(boolean isHistory) {
        historyAdapter = new HistoryAdapter(getActivity(), this, isHistory);
        LinearLayoutManager saloonListManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rv_bookings.setLayoutManager(saloonListManager);
        rv_bookings.setAdapter(historyAdapter);
        rv_bookings.setNestedScrollingEnabled(false);
    }

    private void setUpTabs() {
        bookingsTabs.addTab(bookingsTabs.newTab().setText("UpComing Bookings"));
        bookingsTabs.addTab(bookingsTabs.newTab().setText("History"));
        bookingsTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        setUpRecyclerview(false);
                        break;
                    case 1:
                        setUpRecyclerview(true);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void getProfileDetailsSuccess(ProfileResponse profileResponse) {
        if (profileResponse == null) {
            showToast("Failed to get the profile details");
            return;
        }
        if (profileResponse.getStatus().contains("fail")) {
            showToast(profileResponse.getMessage());
            return;
        }
        if (profileResponse.getData() == null || profileResponse.getData().size() == 0) {
            showToast(profileResponse.getMessage());
            return;
        }
        setData(profileResponse.getData());
    }

    private void setData(List<ProfileResponseData> data) {
        ProfileResponseData profileResponseData = data.get(0);
        if (profileResponseData != null) {
            Glide.with(getActivity()).load(APIConstants.IMAGE_BASE_URL +
                    profileResponseData.getProfilePic())
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(iv_profile);
            tv_userName.setText(profileResponseData.getFirstName() + " " + profileResponseData.getLastName());
            tv_mobile.setText("Mobile : " + profileResponseData.getMobileNumber());
            tv_email.setText("Email : " + profileResponseData.getEmailAddress());
        }
    }

    @Override
    public void getProfileDetailsFailed() {
        showToast("Failed to get the profile details");
    }
}