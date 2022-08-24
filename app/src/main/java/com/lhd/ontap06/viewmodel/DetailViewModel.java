package com.lhd.ontap06.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.lhd.ontap06.base.GetObservable;
import com.lhd.ontap06.constant.Constant;
import com.lhd.ontap06.model.modelzip.ModelZip2;
import com.lhd.ontap06.model.movieModel.DetailMovie;
import com.lhd.ontap06.model.response.CastResponse;
import com.lhd.ontap06.network.ApiService;
import com.lhd.ontap06.network.RetroClient;
import com.lhd.ontap06.until.Until;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.BiFunction;

public class DetailViewModel extends AndroidViewModel {

    private static final String TAG = DetailViewModel.class.getSimpleName();
    private MutableLiveData<DetailMovie> detailMovieMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ModelZip2<DetailMovie, CastResponse>> zip2MutableLiveData = new MutableLiveData<>();
    private ApiService apiService;

    public DetailViewModel(@NonNull Application application) {
        super(application);
        apiService = RetroClient.getAPIService();
    }

    public MutableLiveData<DetailMovie> getDetailMovieMutableLiveData() {
        return detailMovieMutableLiveData;
    }

    public void setDetailMovieMutableLiveData(MutableLiveData<DetailMovie> detailMovieMutableLiveData) {
        this.detailMovieMutableLiveData = detailMovieMutableLiveData;
    }

    public MutableLiveData<ModelZip2<DetailMovie, CastResponse>> getZip2MutableLiveData() {
        return zip2MutableLiveData;
    }

    public void setZip2MutableLiveData(MutableLiveData<ModelZip2<DetailMovie, CastResponse>> zip2MutableLiveData) {
        this.zip2MutableLiveData = zip2MutableLiveData;
    }

    public void getDetailMovie(int id) {
        Until.scheUtils(Observable.zip(getDetail(id), getActor(id), (BiFunction<DetailMovie, CastResponse, ModelZip2>) ModelZip2::new))
                .subscribe(new GetObservable<ModelZip2>() {
                    @Override
                    public void onSuccess(ModelZip2 result) {
                        zip2MutableLiveData.setValue(result);
                    }

                    @Override
                    public void onError(String msg) {
                        Log.e(TAG, "onError: " + msg);
                    }
                });
    }

    private Observable<DetailMovie> getDetail(int id) {
        return Until.scheUtils(apiService.getDetailMovie(id, Constant.KEY, Constant.LANGUAGE));
    }

    private Observable<CastResponse> getActor(int id) {
        return Until.scheUtils(apiService.getActorByIdMovie(String.valueOf(id), Constant.KEY));
    }
}
