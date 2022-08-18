package com.lhd.ontap06.model.movieModel;

import java.util.List;

public class ListCategoriesMovie {
    private String title;
    private List<Movie> lsMovies;

    public ListCategoriesMovie(String title, List<Movie> lsMovies) {
        this.title = title;
        this.lsMovies = lsMovies;
    }

    public ListCategoriesMovie() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Movie> getLsMovies() {
        return lsMovies;
    }

    public void setLsMovies(List<Movie> lsMovies) {
        this.lsMovies = lsMovies;
    }
}
