package com.lhd.ontap06.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.lhd.ontap06.base.GetObservable;
import com.lhd.ontap06.constant.Constant;
import com.lhd.ontap06.model.modelzip.ModelZip4;
import com.lhd.ontap06.model.response.MovieResponse;
import com.lhd.ontap06.network.ApiService;
import com.lhd.ontap06.network.RetroClient;
import com.lhd.ontap06.until.Until;

import io.reactivex.rxjava3.core.Observable;

public class MainViewModel extends AndroidViewModel {
    private ApiService apiService;
    private static final String TAG = MainViewModel.class.getSimpleName();

    private MutableLiveData<ModelZip4<MovieResponse, MovieResponse, MovieResponse, MovieResponse>> zip4MutableLiveData = new MutableLiveData<>();
    private ObservableField<Boolean> isLoadDone = new ObservableField<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.apiService = RetroClient.getAPIService();
    }

    public MutableLiveData<ModelZip4<MovieResponse, MovieResponse, MovieResponse, MovieResponse>> getZip4MutableLiveData() {
        getMovieZip();
        return zip4MutableLiveData;
    }

    public void setZip4MutableLiveData(MutableLiveData<ModelZip4<MovieResponse, MovieResponse, MovieResponse, MovieResponse>> zip4MutableLiveData) {
        this.zip4MutableLiveData = zip4MutableLiveData;
    }


    public void getMovieZip() {
        Until.scheUtils(Observable.zip(getMovie(Constant.POPULAR), getMovie(Constant.UPCOMING), getMovie(Constant.NOW_PLAYING), getMovie(Constant.TOP_RATED),
                        (mvPopular, mvUpcoming, mvNowplaying, mvToprated) -> new ModelZip4<>(mvPopular, mvUpcoming, mvNowplaying, mvToprated)))
                .subscribe(new GetObservable<ModelZip4<MovieResponse, MovieResponse, MovieResponse, MovieResponse>>() {
                    @Override
                    public void onSuccess(ModelZip4<MovieResponse, MovieResponse, MovieResponse, MovieResponse> result) {
                        zip4MutableLiveData.setValue(result);
                        isLoadDone.set(true);
                    }

                    @Override
                    public void onError(String msg) {
                        Log.e(TAG, "Loi : " + msg);
                    }
                });
    }

    private Observable<MovieResponse> getMovie(String option) {
        int numPage = 1;
        return Until.scheUtils(apiService.getMovieByOption(option, Constant.KEY, Constant.LANGUAGE, String.valueOf(numPage)));
    }

    public ObservableField<Boolean> getIsLoadDone() {
        return isLoadDone;
    }

    public void setIsLoadDone(ObservableField<Boolean> isLoadDone) {
        this.isLoadDone = isLoadDone;
    }
}
