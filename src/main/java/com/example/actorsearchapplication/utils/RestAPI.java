package com.example.actorsearchapplication.utils;
import com.example.actorsearchapplication.response.ActorDetailResponse;
import com.example.actorsearchapplication.response.FilmographyResponse;
import com.example.actorsearchapplication.response.SearchPopularActorResponse;
import com.example.actorsearchapplication.response.SearchTrendResponse;

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

    @GET("trending/all/week")
    Call<SearchTrendResponse> getTrends(
            @Query("api_key") String apiKey,
            @Query("page") String pageNum
    );

    @GET("person/{person_id}")
    Call<ActorDetailResponse> getActorDetail(
            @Path("person_id" ) int actor_id,
            @Query("api_key") String api_key
    );

    @GET("person/{person_id}/combined_credits")
    Call<FilmographyResponse> getFlimography(
            @Path("person_id") int actor_id,
            @Query("api_key") String api_key
    );

}
