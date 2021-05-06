package com.saloonme.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.saloonme.R;
import com.saloonme.ui.activities.FeedUploadActivity;
import com.saloonme.ui.adapters.FeedAdapter;

import java.io.File;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class ClickAndShareFragment extends BaseFragment {

    private View view;
    @BindView(R.id.postRv)
    RecyclerView postRv;
    @BindView(R.id.addFeed)
    FloatingActionButton addFeed;

    private FeedAdapter feedAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_click_and_share;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        initRecyclerview();
        return view;
    }

    private void initRecyclerview() {
        feedAdapter = new FeedAdapter(getActivity());
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
}