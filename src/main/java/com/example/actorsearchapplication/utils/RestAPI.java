package com.example.actorsearchapplication.utils;
import com.example.actorsearchapplication.response.ActorDetailResponse;
import com.example.actorsearchapplication.response.CastingResponse;
import com.example.actorsearchapplication.response.FilmographyResponse;
import com.example.actorsearchapplication.response.MovieDetailResponse;
import com.example.actorsearchapplication.response.MoviePopularResponse;
import com.example.actorsearchapplication.response.SearchPopularActorResponse;
import com.example.actorsearchapplication.response.TvDetailResponse;
import com.example.actorsearchapplication.response.TvPopularResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestAPI {

    @GET("person/popular")
    Call<SearchPopularActorResponse> getPopularActors(
            @Query("api_key") String apiKey,
            @Query("page") String pageNum
    );

    @GET("movie/popular")
    Call<MoviePopularResponse> getMovies (
            @Query("api_key") String apiKey,
            @Query("page") String pageNum
    );

    @GET("tv/popular")
    Call<TvPopularResponse> getTvs(
            @Query("api_key") String apiKey,
            @Query("page") String pageNum
    );

    @GET("person/{person_id}")
    Call<ActorDetailResponse> getActorDetail(
            @Path("person_id" ) int actor_id,
            @Query("api_key") String api_key
    );

    @GET("person/{person_id}/combined_credits")
    Call<FilmographyResponse> getFilmography(
            @Path("person_id") int actor_id,
            @Query("api_key") String api_key
    );

    @GET("movie/{movie_id}")
    Call<MovieDetailResponse> getMovieDetail(
            @Path("movie_id" ) int movie_id,
            @Query("api_key") String api_key
    );

    @GET("movie/{movie_id}/credits")
    Call<CastingResponse> getCastingMovie(
            @Path("movie_id" ) int movie_id,
            @Query("api_key") String api_key
    );

    @GET("tv/{tv_id}")
    Call<TvDetailResponse> getTvDetail(
            @Path("tv_id" ) int tv_id,
            @Query("api_key") String api_key
    );

    @GET("tv/{tv_id}/credits")
    Call<CastingResponse> getCastingTV(
            @Path("tv_id" ) int tv_id,
            @Query("api_key") String api_key
    );
}
