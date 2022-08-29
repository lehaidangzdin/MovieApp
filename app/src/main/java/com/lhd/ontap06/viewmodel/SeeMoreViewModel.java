package com.lhd.ontap06.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.lhd.ontap06.base.GetObservable;
import com.lhd.ontap06.constant.Constant;
import com.lhd.ontap06.model.movieModel.Movie;
import com.lhd.ontap06.model.response.MovieResponse;
import com.lhd.ontap06.network.ApiService;
import com.lhd.ontap06.network.RetroClient;

import java.util.List;

public class SeeMoreViewModel extends AndroidViewModel {
    private ApiService apiService;
    private MutableLiveData<List<Movie>> lsgetMovie = new MutableLiveData<>();
    private MutableLiveData<String> mess = new MutableLiveData<>();

    public SeeMoreViewModel(@NonNull Application application) {
        super(application);
        apiService = RetroClient.getAPIService();
    }

    public void loadMoreMovie(String option, int numPage) {
        apiService.getMovieByOption(option, Constant.KEY, Constant.LANGUAGE, String.valueOf(numPage))
                .subscribe(new GetObservable<MovieResponse>() {
                    @Override
                    public void onSuccess(MovieResponse result) {
                        lsgetMovie.setValue(result.getResults());
                    }

                    @Override
                    public void onError(String msg) {
                        mess.setValue(msg);
                    }
                });
    }


    public MutableLiveData<List<Movie>> getLsCategoriesMovieMutableLiveData() {
        return lsgetMovie;
    }

    public void setLsCategoriesMovieMutableLiveData(MutableLiveData<List<Movie>> lsCategoriesMovieMutableLiveData) {
        this.lsgetMovie = lsCategoriesMovieMutableLiveData;
    }

    public MutableLiveData<List<Movie>> getLsgetMovie() {
        return lsgetMovie;
    }

    public void setLsgetMovie(MutableLiveData<List<Movie>> lsgetMovie) {
        this.lsgetMovie = lsgetMovie;
    }

    public MutableLiveData<String> getMess() {
        return mess;
    }

    public void setMess(MutableLiveData<String> mess) {
        this.mess = mess;
    }
}
