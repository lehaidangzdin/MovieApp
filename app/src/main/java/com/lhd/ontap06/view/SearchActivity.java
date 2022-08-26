package com.lhd.ontap06.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.lhd.ontap06.R;
import com.lhd.ontap06.adapter.SearchAdapter;
import com.lhd.ontap06.constant.Constant;
import com.lhd.ontap06.databinding.ActivitySearchBinding;
import com.lhd.ontap06.model.movieModel.ResultsSearch;
import com.lhd.ontap06.model.movieModel.Search;
import com.lhd.ontap06.viewmodel.SearchViewModel;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchAdapter.IOnClickItem {

    private ActivitySearchBinding binding;
    private SearchViewModel searchViewModel;
    private Search search;
    private SearchAdapter searchAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        search = new Search();
        searchViewModel = new SearchViewModel(getApplication());
        binding.setViewModel(searchViewModel);

        binding.setModel(search);
        searchViewModel.getLsMovie().observe(this, this::displayResultsMovie);
    }

    private void displayResultsMovie(List<ResultsSearch> resultsSearches) {
        searchAdapter = new SearchAdapter(resultsSearches, this);
        binding.setAdapter(searchAdapter);
    }

    @Override
    public void onClickItem(ResultsSearch resultsSearch) {
        Intent i = new Intent(SearchActivity.this, DetailActivity.class);
        i.putExtra(Constant.KEY_INTENT_MOVIE, resultsSearch.getId());
        startActivity(i);
    }

    public void backActivity(View view) {
        finish();
    }
}