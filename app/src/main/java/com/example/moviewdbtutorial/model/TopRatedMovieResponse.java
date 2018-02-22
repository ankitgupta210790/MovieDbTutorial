package com.example.moviewdbtutorial.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopRatedMovieResponse {

    @Expose
    @SerializedName("page")
    public int page;

    @Expose
    @SerializedName("total_pages")
    public int totalPages;
    @Expose
    @SerializedName("results")
    public List<Movie_detail> results;

    @Expose
    @SerializedName("total_results")
    public int totalResults;

}
