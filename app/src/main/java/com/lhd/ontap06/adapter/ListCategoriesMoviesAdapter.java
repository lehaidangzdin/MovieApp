package com.lhd.ontap06.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lhd.ontap06.constant.Constant;
import com.lhd.ontap06.databinding.ItemLstCatogeriesMovieBinding;
import com.lhd.ontap06.model.movieModel.ListCategoriesMovie;

import java.util.List;

public class ListCategoriesMoviesAdapter extends RecyclerView.Adapter<ListCategoriesMoviesAdapter.ViewHolder> {
    private final List<ListCategoriesMovie> lsMvZip;
    private final MovieAdapter.IOnClickItem callBack;

    public ListCategoriesMoviesAdapter(List<ListCategoriesMovie> lsMvZip, MovieAdapter.IOnClickItem callBack) {
        this.lsMvZip = lsMvZip;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLstCatogeriesMovieBinding itemLstCatogeriesMovieBinding = ItemLstCatogeriesMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemLstCatogeriesMovieBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ListCategoriesMovie categoriesMovie = lsMvZip.get(position);
        if (categoriesMovie == null) return;
        holder.itemLstCatogeriesMovieBinding.setItem(categoriesMovie);
        MovieAdapter movieAdapter = new MovieAdapter(categoriesMovie.getLsMovies(), this.callBack, Constant.TYPE_LIST_HORIZONTAL);
        holder.itemLstCatogeriesMovieBinding.setAdapter(movieAdapter);
//        holder.itemLstCatogeriesMovieBinding.rcv.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
////                if (!isLoading) {
//                if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == categoriesMovie.getLsMovies().size() - 1) {
//                    //bottom of list!
//                    Log.e("TAG", "onScrolled: END");
////                    }
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return lsMvZip != null ? lsMvZip.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemLstCatogeriesMovieBinding itemLstCatogeriesMovieBinding;

        public ViewHolder(@NonNull ItemLstCatogeriesMovieBinding itemLstCatogeriesMovieBinding) {
            super(itemLstCatogeriesMovieBinding.getRoot());
            this.itemLstCatogeriesMovieBinding = itemLstCatogeriesMovieBinding;
        }
    }

}
