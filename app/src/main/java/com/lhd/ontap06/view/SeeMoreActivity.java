package com.lhd.ontap06.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.lhd.ontap06.R;
import com.lhd.ontap06.adapter.MovieAdapter;
import com.lhd.ontap06.constant.Constant;
import com.lhd.ontap06.databinding.ActivitySeeMoreBinding;
import com.lhd.ontap06.model.movieModel.ListCategoriesMovie;
import com.lhd.ontap06.model.movieModel.Movie;
import com.lhd.ontap06.viewmodel.SeeMoreViewModel;

import java.util.ArrayList;
import java.util.List;

public class SeeMoreActivity extends AppCompatActivity implements MovieAdapter.IOnClickItem {
    private ActivitySeeMoreBinding binding;
    private SeeMoreViewModel seeMoreViewModel;
    private ListCategoriesMovie listCategoriesMovie;
    private boolean isLoading = false;
    private int numPage = 1;
    private final List<Movie> movieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_see_more);
        seeMoreViewModel = new SeeMoreViewModel(getApplication());
        getDataFromActivity();
//        loadMore();
        //
//        seeMoreViewModel.getMess().observe(this, s -> Toast.makeText(SeeMoreActivity.this, s, Toast.LENGTH_SHORT).show());
//        //
//        seeMoreViewModel.getLsgetMovie().observe(this, movies -> {
//            movies.addAll(movies);
//            disPlayInfo(movieList);
//        });
    }

    private void getDataFromActivity() {
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        if (bundle == null) return;
        listCategoriesMovie = (ListCategoriesMovie) bundle.getSerializable(Constant.KEY_INTENT_LIST_MOVIE);

        movieList.addAll(listCategoriesMovie.getLsMovies());
        disPlayInfo(movieList);
    }

    private void disPlayInfo(List<Movie> movieList) {
        binding.setModel(listCategoriesMovie);
        MovieAdapter adapter = new MovieAdapter(movieList, this, Constant.TYPE_LIST_VERTICAL);
        binding.setAdapter(adapter);
    }


    public void backActivity(View view) {
        finish();
    }

    @Override
    public void clickItem(Movie movie) {
        Intent in = new Intent(SeeMoreActivity.this, DetailActivity.class);
        in.putExtra(Constant.KEY_INTENT_MOVIE, movie.getId());
        startActivity(in);
    }

//    private void loadMore() {
//        binding.rcv.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//
//                if (!isLoading) {
//                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == listCategoriesMovie.getLsMovies().size() - 1) {
//                        //bottom of list!
//                        seeMoreViewModel.loadMoreMovie(listCategoriesMovie.getTitle(), numPage);
//                        isLoading = true;
//                        Toast.makeText(SeeMoreActivity.this, "end", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });
//    }
}