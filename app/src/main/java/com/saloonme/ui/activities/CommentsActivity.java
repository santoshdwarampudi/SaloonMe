package com.saloonme.ui.activities;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.saloonme.R;

import butterknife.BindView;

public class CommentsActivity extends BaseAppCompactActivity {



    @BindView(R.id.commentsRv)
    RecyclerView commentsRv;
    @BindView(R.id.newComment)
    EditText newComment;
    @BindView(R.id.icSendCommentTc)
    ImageView icSendCommentTc;

    @Override
    public int getActivityLayout() {
        return  R.layout.activity_comments;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}