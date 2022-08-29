package com.lhd.ontap06.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lhd.ontap06.databinding.ItemMovieBinding;
import com.lhd.ontap06.databinding.ItemSimilarMovieBinding;
import com.lhd.ontap06.model.movieModel.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_VERTICAL = 0;
    private static final int TYPE_HORIZONTAL = 1;

    private List<Movie> lsMovies;
    private IOnClickItem iOnClickItem;
    private int typeOfList;

    public MovieAdapter(List<Movie> lsMovies, IOnClickItem iOnClickItem, int typeOfList) {
        this.lsMovies = lsMovies;
        this.iOnClickItem = iOnClickItem;
        this.typeOfList = typeOfList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_VERTICAL) {
            ItemSimilarMovieBinding binding = ItemSimilarMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ViewMovieVertical(binding);
        } else if (viewType == TYPE_HORIZONTAL) {
            ItemMovieBinding itemMovieBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ViewMovieHorizontal(itemMovieBinding);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Movie movie = lsMovies.get(position);
        if (movie == null) return;
        if (TYPE_VERTICAL == holder.getItemViewType()) {
            ViewMovieVertical viewMovieVertical = (ViewMovieVertical) holder;
            viewMovieVertical.itemSimilarMovieBinding.setItem(movie);
            viewMovieVertical.itemSimilarMovieBinding.setIclick(iOnClickItem);
        } else if (TYPE_HORIZONTAL == holder.getItemViewType()) {
            ViewMovieHorizontal viewMovieHorizontal = (ViewMovieHorizontal) holder;
            viewMovieHorizontal.itemMovieBinding.setItem(movie);
            viewMovieHorizontal.itemMovieBinding.setIclick(iOnClickItem);
        }


    }

    @Override
    public int getItemViewType(int position) {
        if (typeOfList == 1) {
            return TYPE_HORIZONTAL;
        } else {
            return TYPE_VERTICAL;
        }
    }

    @Override
    public int getItemCount() {
        return lsMovies != null ? lsMovies.size() : 0;
    }

    public static class ViewMovieHorizontal extends RecyclerView.ViewHolder {
        ItemMovieBinding itemMovieBinding;

        public ViewMovieHorizontal(@NonNull ItemMovieBinding itemMovieBinding) {
            super(itemMovieBinding.getRoot());
            this.itemMovieBinding = itemMovieBinding;
        }

    }

    public static class ViewMovieVertical extends RecyclerView.ViewHolder {
        ItemSimilarMovieBinding itemSimilarMovieBinding;

        public ViewMovieVertical(@NonNull ItemSimilarMovieBinding itemSimilarMovieBinding) {
            super(itemSimilarMovieBinding.getRoot());
            this.itemSimilarMovieBinding = itemSimilarMovieBinding;
        }
    }

    public interface IOnClickItem {
        void clickItem(Movie movie);
    }

}
