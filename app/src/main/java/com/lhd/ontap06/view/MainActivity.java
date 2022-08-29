package com.lhd.ontap06.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.lhd.ontap06.R;
import com.lhd.ontap06.adapter.ListCategoriesMoviesAdapter;
import com.lhd.ontap06.constant.Constant;
import com.lhd.ontap06.databinding.ActivityMainBinding;
import com.lhd.ontap06.model.modelzip.ModelZip4;
import com.lhd.ontap06.model.movieModel.ListCategoriesMovie;
import com.lhd.ontap06.model.movieModel.Movie;
import com.lhd.ontap06.model.movieModel.PhotoSlide;
import com.lhd.ontap06.model.response.MovieResponse;
import com.lhd.ontap06.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ListCategoriesMoviesAdapter.ClickSeeMore {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private List<PhotoSlide> lsPhotoSlides = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //
        MainViewModel mainViewModel = new MainViewModel(getApplication());
        mainViewModel.getZip4MutableLiveData().observe(this, this::getNewsZip);
        binding.setViewModel(mainViewModel);
        mainViewModel.getMess().observe(this, s -> Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show());
    }

    private void displayMvList(List<ListCategoriesMovie> lsListCategoriesMovies) {
        ListCategoriesMoviesAdapter adapter = new ListCategoriesMoviesAdapter(lsListCategoriesMovies, this::goToDetail, this::onClickSeeMore);
        binding.setAdapter(adapter);
    }


    public void getNewsZip(@NonNull ModelZip4<MovieResponse, MovieResponse, MovieResponse, MovieResponse> dataMovie) {
        if (dataMovie.getRes1() == null) {
            return;
        }
        List<ListCategoriesMovie> lsListCategoriesMovies = new ArrayList<>();
        lsListCategoriesMovies.add(new ListCategoriesMovie(Constant.TITLE_POPULAR, dataMovie.getRes1().getResults()));
        lsListCategoriesMovies.add(new ListCategoriesMovie(Constant.TITLE_UPCOMING, dataMovie.getRes2().getResults()));
        lsListCategoriesMovies.add(new ListCategoriesMovie(Constant.TITLE_NOW_PLAYING, dataMovie.getRes3().getResults()));
        lsListCategoriesMovies.add(new ListCategoriesMovie(Constant.TITLE_TOP_RATED, dataMovie.getRes4().getResults()));
        displayMvList(lsListCategoriesMovies);
    }

    public void goToSearchActivity(View view) {
        Intent i = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(i);
    }

    private void goToDetail(@NonNull Movie movie) {
        Intent in = new Intent(MainActivity.this, DetailActivity.class);
        in.putExtra(Constant.KEY_INTENT_MOVIE, movie.getId());
        startActivity(in);
    }

    @Override
    public void onClickSeeMore(ListCategoriesMovie listCategoriesMovie) {
        Intent i = new Intent(MainActivity.this, SeeMoreActivity.class);
        Bundle b = new Bundle();
        b.putSerializable(Constant.KEY_INTENT_LIST_MOVIE, listCategoriesMovie);
        i.putExtras(b);
        startActivity(i);
    }
}