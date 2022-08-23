package com.lhd.ontap06.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.lhd.ontap06.base.GetObservable;
import com.lhd.ontap06.constant.Constant;
import com.lhd.ontap06.model.movieModel.ResultsSearch;
import com.lhd.ontap06.model.response.SearchMovieResponse;
import com.lhd.ontap06.network.ApiService;
import com.lhd.ontap06.network.RetroClient;
import com.lhd.ontap06.until.Until;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;

public class SearchViewModel extends AndroidViewModel {
    private final String TAG = SearchViewModel.class.getSimpleName();

    private MutableLiveData<List<ResultsSearch>> lsMovie = new MutableLiveData<>();
    private ObservableField<Boolean> isSearch = new ObservableField<>();
    private ObservableField<String> message = new ObservableField<>();
    private ApiService apiService;


    public SearchViewModel(@NonNull Application application) {
        super(application);
        this.apiService = RetroClient.getAPIService();
        message.set("Enter key word to search!");
    }

    public MutableLiveData<List<ResultsSearch>> getLsMovie() {
        return lsMovie;
    }

    public void setLsMovie(MutableLiveData<List<ResultsSearch>> lsMovie) {
        this.lsMovie = lsMovie;
    }

    public ObservableField<Boolean> getIsSearch() {
        return isSearch;
    }

    public void setIsSearch(ObservableField<Boolean> isSearch) {
        this.isSearch = isSearch;
    }

    public ObservableField<String> getMessage() {
        return message;
    }

    public void setMessage(ObservableField<String> message) {
        this.message = message;
    }

    public void searchTitleMovie(String title) {
        if (title.length() == 0) {
            isSearch.set(false);
            message.set("Enter key word to search!");
        } else {
            isSearch.set(true);
            message.set("");
            searchMovie(title);
        }
    }

    private void searchMovie(String title) {
        int numPage = 1;
        Until.scheUtils(apiService.searchMovie(Constant.KEY, String.valueOf(numPage), title)).subscribe(new GetObservable<SearchMovieResponse>() {
            @Override
            public void onSuccess(SearchMovieResponse result) {
                isSearch.set(false);
                lsMovie.setValue(result.getResults());
                if (result.getTotalResults() <= 0) {
                    message.set("Movie not found!");
                } else {
                    message.set("");
                }
            }

            @Override
            public void onError(String msg) {
                Log.e(TAG, "onError: " + msg);
            }
        });
    }


}
