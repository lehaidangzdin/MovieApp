package com.lhd.ontap06.repositories;

import android.util.Log;

import com.lhd.ontap06.base.GetObservable;
import com.lhd.ontap06.constant.Constant;
import com.lhd.ontap06.model.ModelZip4;
import com.lhd.ontap06.model.response.MovieResponse;
import com.lhd.ontap06.network.ApiService;
import com.lhd.ontap06.until.Until;
import com.lhd.ontap06.viewmodel.MainViewModel;

import io.reactivex.rxjava3.core.Observable;

public class MovieRepo {

    private static final String TAG = MovieRepo.class.getSimpleName();
    private ApiService apiService;
    private int numPage = 1;
    private MainViewModel mainViewModel;

    public MovieRepo(MainViewModel model) {
        this.apiService = Constant.getAPIService();
        this.mainViewModel = model;
    }

    public void getMovieZip() {
        Until.scheUtils(Observable.zip(getMovie(Constant.POPULAR), getMovie(Constant.UPCOMING), getMovie(Constant.NOW_PLAYING), getMovie(Constant.TOP_RATED),
                        (mvPopular, mvUpcoming, mvNowplaying, mvToprated) -> {
                            ModelZip4<MovieResponse, MovieResponse, MovieResponse, MovieResponse>
                                    responseModelZip4 = new ModelZip4<>(mvPopular, mvNowplaying, mvToprated, mvUpcoming);
                            return responseModelZip4;
                        }))
                .subscribe(new GetObservable<ModelZip4<MovieResponse, MovieResponse, MovieResponse, MovieResponse>>() {
                    @Override
                    public void onSuccess(ModelZip4<MovieResponse, MovieResponse, MovieResponse, MovieResponse> result) {
                        mainViewModel.getModelZip4ObservableField().set(result);
                    }

                    @Override
                    public void onError(String msg) {
                        Log.e(TAG, "Loi : " + msg);
                    }
                });
    }

    private Observable<MovieResponse> getMovie(String option) {
        return Until.scheUtils(apiService.getMovieByOption(option, Constant.KEY, Constant.LANGUAGE, String.valueOf(numPage)));
    }
}
