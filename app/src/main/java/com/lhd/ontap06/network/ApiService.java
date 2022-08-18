package com.lhd.ontap06.network;

import com.lhd.ontap06.model.movieModel.DetailMovie;
import com.lhd.ontap06.model.response.MovieResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {


    //https://api.themoviedb.org/3/movie/now_playing?api_key=e9e9d8da18ae29fc430845952232787c&language=en-US&page=1
    @GET("movie/{option}")
    Observable<MovieResponse> getMovieByOption(@Path("option") String option,
                                               @Query("api_key") String apiKey,
                                               @Query("language") String language,
                                               @Query("page") String page);

    //https://api.themoviedb.org/3/movie/297762?api_key=e9e9d8da18ae29fc430845952232787c&append_to_response=videos
    @GET("movie/{id}")
    Observable<DetailMovie> getDetailMovie(@Path("id") int id,
                                           @Query("api_key") String apiKey,
                                           @Query("append_to_response") String appendToResponse);
}
