package com.lhd.ontap06.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.lhd.ontap06.base.GetObservable;
import com.lhd.ontap06.constant.Constant;
import com.lhd.ontap06.model.movieModel.DetailMovie;
import com.lhd.ontap06.network.ApiService;
import com.lhd.ontap06.until.Until;

import io.reactivex.rxjava3.core.Observable;

public class DetailViewModel extends AndroidViewModel {

    private static final String TAG = DetailViewModel.class.getSimpleName();
    private MutableLiveData<DetailMovie> detailMovieMutableLiveData = new MutableLiveData<>();
    private ApiService apiService;

    public DetailViewModel(@NonNull Application application) {
        super(application);
        apiService = Constant.getAPIService();
    }

    public MutableLiveData<DetailMovie> getDetailMovieMutableLiveData() {
        return detailMovieMutableLiveData;
    }

    public void setDetailMovieMutableLiveData(MutableLiveData<DetailMovie> detailMovieMutableLiveData) {
        this.detailMovieMutableLiveData = detailMovieMutableLiveData;
    }

    public void getDetailMovie(int id) {
        getDetail(id).subscribe(new GetObservable<DetailMovie>() {
            @Override
            public void onSuccess(DetailMovie result) {
                detailMovieMutableLiveData.setValue(result);
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
}
