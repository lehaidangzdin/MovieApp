package com.lhd.ontap06.network;

import com.lhd.ontap06.model.movieModel.DetailMovie;
import com.lhd.ontap06.model.response.CastResponse;
import com.lhd.ontap06.model.response.MovieResponse;
import com.lhd.ontap06.model.response.SearchMovieResponse;

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

    //https://api.themoviedb.org/3/search/keyword?api_key=e9e9d8da18ae29fc430845952232787c&page=1&query=women
    @GET("search/keyword")
    Observable<SearchMovieResponse> searchMovie(@Query("api_key") String apiKey,
                                                @Query("page") String page,
                                                @Query("query") String query);

    //http://api.themoviedb.org/3/movie/297762/casts?api_key=e9e9d8da18ae29fc430845952232787c
    @GET("movie/{id}/casts")
    Observable<CastResponse> getActorByIdMovie(@Path("id") String id,
                                               @Query("api_key") String apiKey);


    // get similar movie
    //https://api.themoviedb.org/3/movie/297762/similar?api_key=e9e9d8da18ae29fc430845952232787c&language=en-US&page=1
    @GET("movie/{id}/similar")
    Observable<MovieResponse> getSimilarMovie(@Path("id") String id,
                                             @Query("api_key") String apiKey,
                                             @Query("language") String language,
                                             @Query("page") String page);
}
