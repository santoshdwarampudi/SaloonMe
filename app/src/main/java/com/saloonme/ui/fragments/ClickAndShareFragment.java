package com.saloonme.ui.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.saloonme.R;
import com.saloonme.interfaces.IClickAndShareView;
import com.saloonme.model.response.FavouriteResponse;
import com.saloonme.model.response.FeedListResponse;
import com.saloonme.model.response.FeedResponse;
import com.saloonme.network.APIClient;
import com.saloonme.presenters.ClickAndSharePresenter;
import com.saloonme.presenters.HomePresenter;
import com.saloonme.ui.activities.CommentsActivity;
import com.saloonme.ui.activities.FeedUploadActivity;
import com.saloonme.ui.adapters.FeedAdapter;
import com.saloonme.util.PrefUtils;
import com.saloonme.util.ShareUtil;
import com.saloonme.util.ValidationUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class ClickAndShareFragment extends BaseFragment
        implements IClickAndShareView, FeedAdapter.FeedItemListener,
        ShareUtil.onAudioFileShareListener {

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
        initPhotoError();
        clickAndSharePresenter.getFeeds(PrefUtils.getInstance().getUserId());
        return view;
    }

    private void initRecyclerview() {
        feedAdapter = new FeedAdapter(getActivity(), this);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,
                false);
        postRv.setLayoutManager(linearLayoutManager);
        postRv.setItemAnimator(new DefaultItemAnimator());
        postRv.setAdapter(feedAdapter);
    }

    @OnClick(R.id.addFeed)
    void onAddFeed() {
        Intent i = new Intent(getContext(), FeedUploadActivity.class);
        startActivity(i);
    }

    @Override
    public void feedListFetchedSuccess(FeedResponse feedResponse) {
        if (feedResponse == null) {
            showToast("Failed to fetch the feeds");
            return;
        }
        if (!ValidationUtil.isNullOrEmpty(feedResponse.getStatus()) &&
                feedResponse.getStatus().toLowerCase().contains("fail")) {
            showToast(feedResponse.getMessage());
            return;
        }
        if (feedResponse.getData() == null || feedResponse.getData().size() == 0) {
            showToast(feedResponse.getMessage());
            return;
        }
        feedAdapter.setData(feedResponse.getData());


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
        showToast("Failed to add to favourites");
    }


    @Override
    public void onFavouriteClick(String feedSno, String feedUserId) {

        if (feedSno != null && !feedSno.isEmpty() && feedUserId != null && !feedUserId.isEmpty()) {
            clickAndSharePresenter.addFavourite(feedSno, feedUserId);
        }
    }

    @Override
    public void onCommentClick(FeedListResponse feedListResponse) {
        Intent i = new Intent(getContext(), CommentsActivity.class);
        i.putExtra("feed_sno", feedListResponse.getFeedSno());
        startActivity(i);
    }

    @Override
    public void onShareClicked(FeedListResponse feedListResponse, ImageView imageView) {
        shareContent(feedListResponse.getFeedDes(),imageView);

    }

    /*@Override
    public void onShareClicked(FeedListResponse feedListResponse) {
        ShareUtil shareUtil = new ShareUtil(getActivity(), this::onShareListener);
        if (feedListResponse != null) {
            if (!ValidationUtil.isNullOrEmpty(feedListResponse.getFeedImg())) {
                clickAndSharePresenter.showProgress();
                getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                shareUtil.shareImageFile(feedListResponse.getFeedImg(), false);
            }
        }



    }*/

    @Override
    public void onShareListener(boolean sharedSuccessfully) {
        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        clickAndSharePresenter.hideProgress();
    }


    private void shareContent(String feedDes, ImageView imageView){

        if (imageView.getDrawable()!=null){
            Bitmap bitmap =getBitmapFromView(imageView);
            try {
                File file = new File(getActivity().getExternalCacheDir(),"feed.jpg");
                FileOutputStream fOut = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                fOut.flush();
                fOut.close();
                file.setReadable(true, false);
                final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(Intent.EXTRA_TEXT, feedDes);
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                intent.setType("image/png");
                startActivity(Intent.createChooser(intent, "Share image via"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_TEXT, feedDes);
            intent.setType("text/plain");
            startActivity(Intent.createChooser(intent, "Share text via"));
        }
    }

    private Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null) {
            bgDrawable.draw(canvas);
        }   else{
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;
    }

    private void initPhotoError(){
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }

}