package com.lhd.ontap06.viewmodel;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.app.Application;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.lhd.ontap06.base.GetObservable;
import com.lhd.ontap06.constant.Constant;
import com.lhd.ontap06.model.modelzip.ModelZip3;
import com.lhd.ontap06.model.movieModel.DetailMovie;
import com.lhd.ontap06.model.response.CastResponse;
import com.lhd.ontap06.model.response.MovieResponse;
import com.lhd.ontap06.network.ApiService;
import com.lhd.ontap06.network.RetroClient;
import com.lhd.ontap06.until.Until;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function3;

public class DetailViewModel extends AndroidViewModel {

    private static final String TAG = DetailViewModel.class.getSimpleName();
    private ApiService apiService;
    private MutableLiveData<DetailMovie> detailMovieMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ModelZip3<DetailMovie, CastResponse, MovieResponse>> zipSimilarMovie = new MutableLiveData<>();
    private MutableLiveData<Integer> idMovie = new MutableLiveData<>();
    private MutableLiveData<Boolean> isShowMenu = new MutableLiveData<>();
    private MutableLiveData<String> permissionRequest = new MutableLiveData<>();
    private ObservableField<Boolean> isLoading = new ObservableField<>();
    private String url;

    public boolean onLongClickToDownload(String url) {
        isShowMenu.setValue(true);
        this.url = url;
        return false;
    }

    public void downLoadImage() {
        isShowMenu.setValue(false);
        permissionRequest.setValue(WRITE_EXTERNAL_STORAGE);
        Log.e(TAG, "downLoadImage: " + url);
//        startDownload();
    }

    private void startDownload() {

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setTitle("Download...");
        request.setDescription("Download file...");

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, System.currentTimeMillis() + ".png");

        DownloadManager downloadManager = (DownloadManager) getApplication().getSystemService(Context.DOWNLOAD_SERVICE);
        if (downloadManager != null) {
            downloadManager.enqueue(request);
        }
    }

    public void onPermissionResult(boolean granted) {
        if (granted) {
            startDownload();
        }
    }

    public void getDetailMovie(int id) {
        Until.scheUtils(Observable.zip(getDetail(id), getActor(id), getSimilar(id, 1),
                (Function3<DetailMovie, CastResponse, MovieResponse, ModelZip3>) ModelZip3::new)).subscribe(new GetObservable<ModelZip3>() {
            @Override
            public void onSuccess(ModelZip3 result) {
                zipSimilarMovie.setValue(result);
                isLoading.set(true);
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

    private Observable<MovieResponse> getSimilar(int id, int numPage) {
        return Until.scheUtils(apiService.getSimilarMovie(String.valueOf(id), Constant.KEY, Constant.LANGUAGE, String.valueOf(numPage)));
    }

    public MutableLiveData<String> getPermissionRequest() {
        return permissionRequest;
    }

    public void setPermissionRequest(MutableLiveData<String> permissionRequest) {
        this.permissionRequest = permissionRequest;
    }

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

    public MutableLiveData<ModelZip3<DetailMovie, CastResponse, MovieResponse>> getZipSimilarMovie() {
        return zipSimilarMovie;
    }

    public void setZipSimilarMovie(MutableLiveData<ModelZip3<DetailMovie, CastResponse, MovieResponse>> zipSimilarMovie) {
        this.zipSimilarMovie = zipSimilarMovie;
    }

    public MutableLiveData<Integer> getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie.setValue(idMovie);
    }

    public MutableLiveData<Boolean> getIsShowMenu() {
        return isShowMenu;
    }

    public void setIsShowMenu(MutableLiveData<Boolean> isShowMenu) {
        this.isShowMenu = isShowMenu;
    }

    public ObservableField<Boolean> getIsLoading() {
        return isLoading;
    }

    public void setIsLoading(ObservableField<Boolean> isLoading) {
        this.isLoading = isLoading;
    }
}
