package com.saloonme.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.saloonme.R;
import com.saloonme.interfaces.IClickAndShareView;
import com.saloonme.model.response.FavouriteResponse;
import com.saloonme.model.response.FeedListResponse;
import com.saloonme.network.APIClient;
import com.saloonme.presenters.ClickAndSharePresenter;
import com.saloonme.presenters.HomePresenter;
import com.saloonme.ui.activities.CommentsActivity;
import com.saloonme.ui.activities.FeedUploadActivity;
import com.saloonme.ui.adapters.FeedAdapter;
import com.saloonme.util.PrefUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class ClickAndShareFragment extends BaseFragment implements IClickAndShareView, FeedAdapter.FeedItemListener {

    private View view;
    @BindView(R.id.postRv)
    RecyclerView postRv;
    @BindView(R.id.addFeed)
    FloatingActionButton addFeed;

    private FeedAdapter feedAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ClickAndSharePresenter clickAndSharePresenter;

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_click_and_share;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        clickAndSharePresenter = new ClickAndSharePresenter(APIClient.getAPIService(), this);
        initRecyclerview();
        clickAndSharePresenter.getFeeds(PrefUtils.getInstance().getUserId());
        return view;
    }

    private void initRecyclerview() {
        feedAdapter = new FeedAdapter(getActivity(),this);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,
                false);
        postRv.setLayoutManager(linearLayoutManager);
        postRv.setItemAnimator(new DefaultItemAnimator());
        postRv.setAdapter(feedAdapter);
    }

    @OnClick(R.id.addFeed)
    void onAddFeed(){
        Intent i = new Intent(getContext(), FeedUploadActivity.class);
        startActivity(i);
    }

    @Override
    public void feedListFetchedSuccess(List<FeedListResponse> saloonListResponse) {
        if (saloonListResponse != null) {
            feedAdapter.setData(saloonListResponse);
        }

    }

    @Override
    public void feedListFetchedFailed() {
        feedAdapter.setData(null);
        showToast("Failed to fetch the feeds");
    }

    @Override
    public void addFavouriteSuccess(FavouriteResponse favouriteResponse) {
        showToast(favouriteResponse.getMessage());
        clickAndSharePresenter.getFeeds(PrefUtils.getInstance().getUserId());
    }

    @Override
    public void addFavouriteFailed() {

    }


    @Override
    public void onFavouriteClick(String feedSno, String feedUserId) {

        if (feedSno!=null && !feedSno.isEmpty() && feedUserId!=null && !feedUserId.isEmpty()){
            clickAndSharePresenter.addFavourite(feedSno,feedUserId);
        }
    }

    @Override
    public void onCommentClick(FeedListResponse feedListResponse) {
        Intent i = new Intent(getContext(), CommentsActivity.class);
        i.putExtra("feed_sno",feedListResponse.getFeedSno());
        startActivity(i);
    }
}