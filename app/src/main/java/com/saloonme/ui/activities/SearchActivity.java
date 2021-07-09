package com.saloonme.ui.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saloonme.R;
import com.saloonme.interfaces.ISearchView;
import com.saloonme.interfaces.StringConstants;
import com.saloonme.model.response.SaloonListResponseData;
import com.saloonme.model.response.SearchResponse;
import com.saloonme.model.response.SearchResponseData;
import com.saloonme.network.APIClient;
import com.saloonme.presenters.SearchPresenter;
import com.saloonme.ui.adapters.SearchAdapter;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseAppCompactActivity implements SearchAdapter.ItemListener,
        ISearchView {

    private SearchAdapter searchAdapter;
    private String cityId;
    private SearchPresenter searchPresenter;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_search)
    EditText et_search;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_heading)
    TextView tv_heading;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.rv_searchItems)
    RecyclerView rv_searchItems;

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_menu)
    void onBackClick() {
        finish();
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_search;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_heading.setText("Search");
        cityId = getIntent().getStringExtra("city");
        if (cityId == null) {
            return;
        }
        searchPresenter = new SearchPresenter(APIClient.getAPIService(), this);
        setUpRecyclerView();
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchPresenter.searchSaloon(cityId, charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    private void setUpRecyclerView() {
        searchAdapter = new SearchAdapter(getContext(), this);
        LinearLayoutManager saloonListManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        rv_searchItems.setLayoutManager(saloonListManager);
        rv_searchItems.setAdapter(searchAdapter);
        rv_searchItems.setNestedScrollingEnabled(false);
    }

    @Override
    public void searchSuccess(SearchResponse searchResponse) {
        if (searchResponse == null) {
            showToast("No Saloons Found");
            searchAdapter.updateSearchResponse(null);
            return;
        }
        if (searchResponse.getStatus().equals("false") || searchResponse.getData() == null ||
                searchResponse.getData().size() == 0) {
            showToast(searchResponse.getMessage());
            searchAdapter.updateSearchResponse(null);
            return;
        }
        searchAdapter.updateSearchResponse(searchResponse.getData());
    }

    @Override
    public void searchFailed() {
        showToast("No Saloons Found");
        searchAdapter.updateSearchResponse(null);
    }

    @Override
    public void onSearchItemClicked(SaloonListResponseData searchResponseData) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(StringConstants.EXTRA_DETAILS, searchResponseData);
        goToActivity(SaloonDetailsActivity.class, bundle);
    }
}