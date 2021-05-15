package com.saloonme.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.saloonme.R;
import com.saloonme.model.response.CommentListResponse;
import com.saloonme.model.response.FeedComment;
import com.saloonme.model.response.FeedListResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {
    private Context mContext;
    private List<FeedComment> feedComments;

    public CommentsAdapter(Context mContext) {
        this.mContext = mContext;

    }
    public void setData(List<FeedComment> feedCommentList) {
        this.feedComments = feedCommentList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_feed_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.setData(feedComments.get(i));
    }

    @Override
    public int getItemCount() {
        return feedComments != null ? feedComments.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.commentBy)
        TextView commentBy;
        @BindView(R.id.comment)
        TextView comment;
        @BindView(R.id.dateInfoTv)
        TextView dateInfoTv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(FeedComment feedComment) {
            comment.setText(feedComment.getFeedComment());
            commentBy.setText(feedComment.getFirstName());
            dateInfoTv.setText(feedComment.getFeedCreatedDate());
        }

    }

}
