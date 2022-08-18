package com.lhd.ontap06.repositories;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.lhd.ontap06.base.GetObservable;
import com.lhd.ontap06.constant.Constant;
import com.lhd.ontap06.model.movieModel.DetailMovie;
import com.lhd.ontap06.network.ApiService;
import com.lhd.ontap06.until.Until;

import io.reactivex.rxjava3.core.Observable;

public class DetailRepo {
    private ApiService apiService;
    private static final String TAG = DetailRepo.class.getSimpleName();

    public DetailRepo() {
        this.apiService = Constant.getAPIService();
    }

    public MutableLiveData<DetailMovie> getDetailMovie(int id) {
        MutableLiveData<DetailMovie> data = new MutableLiveData<>();
        getMovie(id).subscribe(new GetObservable<DetailMovie>() {
            @Override
            public void onSuccess(DetailMovie result) {
                data.setValue(result);
                Log.e(TAG, "onSuccess: " + result);
            }

            @Override
            public void onError(String msg) {
                Log.e(TAG, "onError: " + msg);
            }
        });
        return data;
    }

    private Observable<DetailMovie> getMovie(int id) {
        return Until.scheUtils(apiService.getDetailMovie(id, Constant.KEY, Constant.APPEND_TO_RESPONSE));
    }
}
