package com.example.moviewdbtutorial.api;

import com.example.moviewdbtutorial.model.Movie_detail;
import com.example.moviewdbtutorial.model.TopRatedMovieResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ankit Gupta on 2/19/2018.
 */

public interface MovieApiService {

    @GET("movie/top_rated")
    Observable<TopRatedMovieResponse> getTopRatedMovies(@Query("api_key") String api_key,
                                                        @Query("language") String language,
                                                        @Query("page") int page);

    @GET("movie/popular")
    Observable<TopRatedMovieResponse> getPopularMovies(@Query("api_key") String api_key,
                                                       @Query("language") String language,
                                                       @Query("page") int page);

    @GET("search/movie")
    Observable<TopRatedMovieResponse> getSearchResult(@Query("api_key") String api_key,
                                                      @Query("query") String query,
                                                      @Query("language") String language,
                                                      @Query("page") int page);

    @GET("movie/{movie_id}")
    Observable<Movie_detail> getMovieDetail(@Path(value = "movie_id", encoded = false) String path,
                                            @Query("api_key") String api_key);
}
