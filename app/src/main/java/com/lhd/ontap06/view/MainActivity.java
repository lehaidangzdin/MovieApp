package com.lhd.ontap06.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.lhd.ontap06.R;
import com.lhd.ontap06.adapter.ListCategoriesMoviesAdapter;
import com.lhd.ontap06.constant.Constant;
import com.lhd.ontap06.databinding.ActivityMainBinding;
import com.lhd.ontap06.model.ModelZip4;
import com.lhd.ontap06.model.movieModel.ListCategoriesMovie;
import com.lhd.ontap06.model.movieModel.Movie;
import com.lhd.ontap06.model.response.MovieResponse;
import com.lhd.ontap06.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private List<ListCategoriesMovie> lsListCategoriesMovies;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //
        mainViewModel = new MainViewModel(getApplication());
        mainViewModel.getZip4MutableLiveData().observe(this, movieResponseMovieResponseMovieResponseMovieResponseModelZip4 -> getNewsZip(movieResponseMovieResponseMovieResponseMovieResponseModelZip4));
    }

    private void displayMvList(List<ListCategoriesMovie> lsListCategoriesMovies) {
        ListCategoriesMoviesAdapter adapter = new ListCategoriesMoviesAdapter(lsListCategoriesMovies, getApplicationContext(), movie -> {
            goToDetail(movie);
        });
        binding.setAdapter(adapter);
    }

    private void goToDetail(Movie movie) {
        Intent in = new Intent(MainActivity.this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("movie", movie);
        in.putExtras(bundle);
        startActivity(in);
    }

    public void getNewsZip(@NonNull ModelZip4<MovieResponse, MovieResponse, MovieResponse, MovieResponse> dataMovie) {
        if (dataMovie.getRes1() == null) {
            return;
        }
        binding.process.setVisibility(View.GONE);
        lsListCategoriesMovies = new ArrayList<>();
        lsListCategoriesMovies.add(new ListCategoriesMovie(Constant.TITLE_POPULAR, dataMovie.getRes1().getResults()));
        lsListCategoriesMovies.add(new ListCategoriesMovie(Constant.TITLE_NOW_PLAYING, dataMovie.getRes2().getResults()));
        lsListCategoriesMovies.add(new ListCategoriesMovie(Constant.TITLE_UPCOMING, dataMovie.getRes3().getResults()));
        lsListCategoriesMovies.add(new ListCategoriesMovie(Constant.TITLE_TOP_RATED, dataMovie.getRes4().getResults()));
        displayMvList(lsListCategoriesMovies);
    }
}