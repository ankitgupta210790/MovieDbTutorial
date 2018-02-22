package com.example.moviewdbtutorial.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.moviewdbtutorial.mvp.presenter.MainPresenter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import retrofit2.http.Query;

@Entity
public class Movie_detail {

    @Expose
    @SerializedName("overview")
    public String overview;
    @Expose
    @SerializedName("original_language")
    public String originalLanguage;
    @Expose
    @SerializedName("original_title")
    public String originalTitle;
    @Expose
    @SerializedName("video")
    public boolean video;
    @Expose
    @SerializedName("title")
    public String title;
    //    @Expose
//    @SerializedName("genre_ids")
//    public List<Integer> genreIds;
    @Expose
    @SerializedName("poster_path")
    public String posterPath;
    @Expose
    @SerializedName("backdrop_path")
    public String backdropPath;
    @Expose
    @SerializedName("release_date")
    public String releaseDate;
    @Expose
    @SerializedName("vote_average")
    public double voteAverage;
    @Expose
    @SerializedName("popularity")
    public double popularity;

    @PrimaryKey(autoGenerate = true)
    @Expose
    @SerializedName("id")
    public int id;
    @Expose
    @SerializedName("adult")
    public boolean adult;

    @Expose
    @SerializedName("vote_count")
    public int voteCount;

    public int category;


    @Override
    public String toString() {
        return this.title + " " + this.releaseDate;

    }
}
