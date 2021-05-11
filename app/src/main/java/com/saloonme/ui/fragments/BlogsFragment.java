package com.saloonme.ui.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.saloonme.R;
import com.saloonme.interfaces.IBlogDetailsView;
import com.saloonme.model.response.BlogDetailsResponse;
import com.saloonme.model.response.SaloonListResponseData;
import com.saloonme.network.APIClient;
import com.saloonme.presenters.BlogDetailsPresenter;
import com.saloonme.ui.adapters.BlogsAdapter;
import com.saloonme.ui.adapters.OffersHorizontalAdapter;
import com.saloonme.ui.adapters.SaloonListAdapter;
import com.saloonme.util.CirclePagerIndicatorDecoration;

import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;


public class BlogsFragment
        extends BaseFragment implements IBlogDetailsView, BlogsAdapter.BlogItemListener {

    private View view;
    private BlogsAdapter blogsAdapter;
    private BlogDetailsPresenter blogDetailsPresenter;
    @BindView(R.id.rv_blogItems)
    RecyclerView rv_blogItems;


    public BlogsFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_salons;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        initRecyclerview();
        blogDetailsPresenter = new BlogDetailsPresenter(APIClient.getAPIService(), this);
        blogDetailsPresenter.getBlogDetails();
        return view;
    }

    @Override
    public void onBlogDetailsSuccess(BlogDetailsResponse blogDetailsResponse) {
        if (blogDetailsResponse == null) {
            showToast("Failed to get the blogs");
            return;
        }
        if (blogDetailsResponse.getStatus().toLowerCase().contains("fail")) {
            showToast(blogDetailsResponse.getMessage());
            return;
        }
        if (blogDetailsResponse.getData() == null || blogDetailsResponse.getData().size() == 0) {
            showToast(blogDetailsResponse.getMessage());
            return;
        }
        blogsAdapter.setData(blogDetailsResponse.getData());
    }

    @Override
    public void onBlogDetailsFailed() {
        showToast("Failed to get the blogs");
    }

    private void initRecyclerview() {
        blogsAdapter = new BlogsAdapter(getContext(), null,
                this);
        LinearLayoutManager saloonListManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rv_blogItems.setLayoutManager(saloonListManager);
        rv_blogItems.setAdapter(blogsAdapter);
        rv_blogItems.setNestedScrollingEnabled(false);
    }


    @Override
    public void onBlogClick() {

    }
}