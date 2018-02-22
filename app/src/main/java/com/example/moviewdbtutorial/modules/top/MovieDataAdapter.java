package com.example.moviewdbtutorial.modules.top;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moviewdbtutorial.R;
import com.example.moviewdbtutorial.model.Movie_detail;
import com.example.moviewdbtutorial.util.Constants;
import com.example.moviewdbtutorial.util.Lg;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ankit Gupta on 2/20/2018.
 */

public class MovieDataAdapter extends RecyclerView.Adapter<MovieDataAdapter.MovieViewHolder> {

    private List<Movie_detail> movieDetailList = new ArrayList<>();
    private LayoutInflater mLayoutInflater;
    private View.OnClickListener mItemClickListener;

    public MovieDataAdapter(LayoutInflater mLayoutInflater) {
        this.mLayoutInflater = mLayoutInflater;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieViewHolder(mLayoutInflater.inflate(R.layout.layout_movie_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

        if (movieDetailList != null && movieDetailList.get(position) != null) {
            Movie_detail movie_detail = movieDetailList.get(position);
            Lg.d("Image URI", movie_detail.posterPath);
            holder.movieIcon.setImageURI(Constants.IMAGE_BASE_URL + movie_detail.posterPath);
            holder.movieIconBackdrop.setImageURI(Constants.IMAGE_BASE_URL + movie_detail.backdropPath);

            holder.description.setText(movie_detail.releaseDate);
            holder.title.setText(movie_detail.title);
            holder.itemView.setTag(movie_detail);
        }
    }

    @Override
    public int getItemCount() {
        return (movieDetailList == null || movieDetailList.isEmpty()) ? 0 : movieDetailList.size();
    }

    public void setClickListener(View.OnClickListener clickListener) {
        mItemClickListener = clickListener;
    }

    public void addData(List<Movie_detail> movies) {
        this.movieDetailList.addAll(movies);
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.movie_title)
        TextView title;
        @BindView(R.id.movie_descrition)
        TextView description;
        @BindView(R.id.movie_icon)
        SimpleDraweeView movieIcon;

        @BindView(R.id.movie_icon_backdrop)
        SimpleDraweeView movieIconBackdrop;

        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onClick(itemView);
            }
        }
    }

    public void setData(List<Movie_detail> movieDetailList) {
        this.movieDetailList = movieDetailList;
    }
}
