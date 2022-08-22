package com.lhd.ontap06.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.lhd.ontap06.R;
import com.lhd.ontap06.adapter.HistoryAdapter;
import com.lhd.ontap06.databinding.ActivitySearchBinding;
import com.lhd.ontap06.model.db.History;
import com.lhd.ontap06.viewmodel.SearchViewModel;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements HistoryAdapter.OnClickItem {

    private ActivitySearchBinding binding;
    private SearchViewModel searchViewModel;
    private History history;
    private HistoryAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        history = new History();
        searchViewModel = new SearchViewModel(getApplication());
        binding.setViewModel(searchViewModel);

        binding.setModel(history);

        searchViewModel.getLsHistory().observe(this, this::displayHistory);
    }

    private void displayHistory(List<History> histories) {
        adapter = new HistoryAdapter(histories, this);
        binding.setAdapter(adapter);
    }

    @Override
    public void onClickItem(History history) {
        Toast.makeText(this, "" + history.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickDeleteItem(History history) {
        searchViewModel.deleteHistory(history.getId());
        adapter.notifyDataSetChanged();
    }
}