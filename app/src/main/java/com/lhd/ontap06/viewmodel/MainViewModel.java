package com.lhd.ontap06.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.lhd.ontap06.model.ModelZip4;
import com.lhd.ontap06.model.response.MovieResponse;
import com.lhd.ontap06.repositories.MovieRepo;

public class MainViewModel extends AndroidViewModel {
    private MutableLiveData<MovieRepo> repoData = new MutableLiveData<>(new MovieRepo(this));
    private ObservableField<ModelZip4<MovieResponse, MovieResponse, MovieResponse, MovieResponse>> modelZip4ObservableField
            =new ObservableField<>();

    public ObservableField<ModelZip4<MovieResponse, MovieResponse, MovieResponse, MovieResponse>> getModelZip4ObservableField() {
        return modelZip4ObservableField;
    }

    public void setModelZip4ObservableField(ObservableField<ModelZip4<MovieResponse, MovieResponse, MovieResponse, MovieResponse>> modelZip4ObservableField) {
        this.modelZip4ObservableField = modelZip4ObservableField;
    }

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<MovieRepo> getRepoData() {
        return repoData;
    }

    public void setRepoData(MutableLiveData<MovieRepo> repoData) {
        this.repoData = repoData;
    }
}
