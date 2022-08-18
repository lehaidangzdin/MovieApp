package com.lhd.ontap06.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.lhd.ontap06.base.GetObservable;
import com.lhd.ontap06.constant.Constant;
import com.lhd.ontap06.model.ModelZip4;
import com.lhd.ontap06.model.response.MovieResponse;
import com.lhd.ontap06.network.ApiService;
import com.lhd.ontap06.until.Until;

import io.reactivex.rxjava3.core.Observable;

public class MainViewModel extends AndroidViewModel {
    private ApiService apiService;
    private static final String TAG = MainViewModel.class.getSimpleName();
    private int numPage = 1;
    private boolean isLoading = true;

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    private MutableLiveData<ModelZip4<MovieResponse, MovieResponse, MovieResponse, MovieResponse>> zip4MutableLiveData;

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.apiService = Constant.getAPIService();
    }

    public MutableLiveData<ModelZip4<MovieResponse, MovieResponse, MovieResponse, MovieResponse>> getZip4MutableLiveData() {
        if (zip4MutableLiveData == null) {
            zip4MutableLiveData = new MutableLiveData<>();
            getMovieZip();
            isLoading = false;

        }
        return zip4MutableLiveData;
    }

    public void setZip4MutableLiveData(MutableLiveData<ModelZip4<MovieResponse, MovieResponse, MovieResponse, MovieResponse>> zip4MutableLiveData) {
        this.zip4MutableLiveData = zip4MutableLiveData;
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
//                        mainViewModel.getModelZip4ObservableField().set(result);
                        zip4MutableLiveData.setValue(result);
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
