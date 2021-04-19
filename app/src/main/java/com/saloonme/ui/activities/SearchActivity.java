package com.saloonme.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.saloonme.R;
import com.saloonme.ui.adapters.SaloonListAdapter;
import com.saloonme.ui.adapters.SearchAdapter;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseAppCompactActivity implements SearchAdapter.ItemListener {

    private SearchAdapter searchAdapter;
    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.tv_heading)
    TextView tv_heading;
    @BindView(R.id.rv_searchItems)
    RecyclerView rv_searchItems;

    @OnClick(R.id.iv_menu)
    void onBackClick() {
        finish();
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_heading.setText("Search");
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        searchAdapter = new SearchAdapter(getContext(), this);
        LinearLayoutManager saloonListManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        rv_searchItems.setLayoutManager(saloonListManager);
        rv_searchItems.setAdapter(searchAdapter);
        rv_searchItems.setNestedScrollingEnabled(false);
    }
}