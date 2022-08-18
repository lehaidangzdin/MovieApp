package com.lhd.ontap06.model.movieModel;

import androidx.databinding.Bindable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lhd.ontap06.constant.Constant;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {
    @SerializedName("adult")
    @Expose
    private boolean adult;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds = null;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("popularity")
    @Expose
    private double popularity;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("video")
    @Expose
    private boolean video;
    @SerializedName("vote_average")
    @Expose
    private double voteAverage;
    @SerializedName("vote_count")
    @Expose
    private int voteCount;

//    @Bindable
    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
//        notifyPropertyChanged(BR.adult);

    }

//    @Bindable
    public String getBackdropPath() {
        String urlBackdropPath = Constant.PATH_IMAGE + backdropPath;
        return urlBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
//        notifyPropertyChanged(BR.backdropPath);
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;

    }

//    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
//        notifyPropertyChanged(BR.id);

    }

//    @Bindable
    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
//        notifyPropertyChanged(BR.originalLanguage);

    }

//    @Bindable
    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
//        notifyPropertyChanged(BR.originalTitle);

    }

//    @Bindable
    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
//        notifyPropertyChanged(BR.overview);

    }

//    @Bindable
    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
//        notifyPropertyChanged(BR.popularity);

    }

//    @Bindable
    public String getPosterPath() {
        String urlImg = Constant.PATH_IMAGE + posterPath;
        return urlImg;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
//        notifyPropertyChanged(BR.posterPath);

    }

//    @Bindable
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
//        notifyPropertyChanged(BR.releaseDate);

    }

//    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
//        notifyPropertyChanged(BR.title);

    }

//    @Bindable
    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
//        notifyPropertyChanged(BR.video);

    }

//    @Bindable
    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
//        notifyPropertyChanged(BR.voteAverage);

    }

//    @Bindable
    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
//        notifyPropertyChanged(BR.voteCount);

    }
}
