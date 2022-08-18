package com.lhd.ontap06.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lhd.ontap06.databinding.ItemMovieBinding;
import com.lhd.ontap06.model.movieModel.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private List<Movie> lsMovies;
    private IOnClickItem iOnClickItem;

    public MovieAdapter(List<Movie> lsMovies, IOnClickItem iOnClickItem) {
        this.lsMovies = lsMovies;
        this.iOnClickItem = iOnClickItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieBinding itemMovieBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemMovieBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Movie movie = lsMovies.get(position);
        if (movie == null) return;
        holder.itemMovieBinding.setItem(movie);
        holder.bind(movie, iOnClickItem);

    }

    @Override
    public int getItemCount() {
        return lsMovies != null ? lsMovies.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemMovieBinding itemMovieBinding;

        public ViewHolder(@NonNull ItemMovieBinding itemMovieBinding) {
            super(itemMovieBinding.getRoot());
            this.itemMovieBinding = itemMovieBinding;
        }

        public void bind(final Movie item, final MovieAdapter.IOnClickItem listener) {
            itemView.setOnClickListener(v -> listener.clickItem(item));
        }
    }

    public interface IOnClickItem {
        void clickItem(Movie movie);
    }
}
