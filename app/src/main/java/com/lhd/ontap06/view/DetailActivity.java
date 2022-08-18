package com.lhd.ontap06.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.lhd.ontap06.R;
import com.lhd.ontap06.databinding.ActivityDetailBinding;
import com.lhd.ontap06.model.movieModel.Movie;
import com.lhd.ontap06.viewmodel.DetailViewModel;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();
    private ActivityDetailBinding binding;
    private DetailViewModel detailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        getDataFromMainActivity();
        detailViewModel.getDetailMovieMutableLiveData().observe(this, detailMovie -> {
            binding.setItem(detailMovie);
        });
    }

    private void getDataFromMainActivity() {
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        Movie movie = (Movie) bundle.getSerializable("movie");
        detailViewModel.getDetailMovie(movie.getId());
        Toast.makeText(this, "" + movie.getTitle(), Toast.LENGTH_SHORT).show();
    }

    public void backActivity(View view) {
        super.onBackPressed();
    }
}