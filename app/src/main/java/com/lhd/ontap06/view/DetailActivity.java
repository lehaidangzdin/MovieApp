package com.lhd.ontap06.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.lhd.ontap06.R;
import com.lhd.ontap06.adapter.CompanyAdapter;
import com.lhd.ontap06.databinding.ActivityDetailBinding;
import com.lhd.ontap06.viewmodel.DetailViewModel;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();
    private ActivityDetailBinding binding;
    private DetailViewModel detailViewModel;
    private CompanyAdapter companyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        getDataFromMainActivity();
        detailViewModel.getDetailMovieMutableLiveData().observe(this, detailMovie -> {
            binding.setItem(detailMovie);
            companyAdapter = new CompanyAdapter(detailMovie.getProductionCompanies());
            binding.setAdapter(companyAdapter);
        });
    }

    private void getDataFromMainActivity() {
        Intent i = getIntent();
        int id = i.getIntExtra("idMovie", 0);
        detailViewModel.getDetailMovie(id);
        Toast.makeText(this, "" + id, Toast.LENGTH_SHORT).show();
    }

    public void backActivity(View view) {
        finish();
    }
}