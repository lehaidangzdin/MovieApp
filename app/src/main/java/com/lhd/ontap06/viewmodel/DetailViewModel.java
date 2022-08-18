package com.lhd.ontap06.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.lhd.ontap06.model.movieModel.DetailMovie;
import com.lhd.ontap06.model.movieModel.Movie;
import com.lhd.ontap06.repositories.DetailRepo;

public class DetailViewModel extends AndroidViewModel {

    private MutableLiveData<Integer> movieMutableLiveData = new MutableLiveData<>();
    private DetailRepo detailRepo;

    public DetailViewModel(@NonNull Application application) {
        super(application);
        detailRepo = new DetailRepo();
    }

    public MutableLiveData<DetailMovie> getMovieMutableLiveData() {
        return detailRepo.getDetailMovie(movieMutableLiveData.getValue());
    }

    public void setMovieData(Movie movie) {
        movieMutableLiveData.setValue(movie.getId());
    }
}
