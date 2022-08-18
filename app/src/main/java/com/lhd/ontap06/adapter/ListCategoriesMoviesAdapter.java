package com.lhd.ontap06.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lhd.ontap06.databinding.ItemLstCatogeriesMovieBinding;
import com.lhd.ontap06.model.movieModel.ListCategoriesMovie;

import java.util.List;

public class ListCategoriesMoviesAdapter extends RecyclerView.Adapter<ListCategoriesMoviesAdapter.ViewHolder> {
    private List<ListCategoriesMovie> lsMvZip;
    private Context context;
    private MovieAdapter.IOnClickItem callBack;

    public ListCategoriesMoviesAdapter(List<ListCategoriesMovie> lsMvZip, Context context, MovieAdapter.IOnClickItem callBack) {
        this.lsMvZip = lsMvZip;
        this.context = context;
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

        holder.itemLstCatogeriesMovieBinding.tvTitle.setText(categoriesMovie.getTitle());
        MovieAdapter movieAdapter = new MovieAdapter(categoriesMovie.getLsMovies(), this.callBack);
        LinearLayoutManager manager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        holder.itemLstCatogeriesMovieBinding.rcv.setLayoutManager(manager);
        holder.itemLstCatogeriesMovieBinding.rcv.setAdapter(movieAdapter);

//        holder.itemLstCatogeriesMovieBinding.rcv.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                //Nếu item cuối cùng của layout = với giá trị cuối của recycleView thì ta gọi hàm LoadMore
//                if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == categoriesMovie.getLsMovies().size() - 1) {
//                    //bottom of list!
//                    Log.e("TAGGGGG", "last item: ");
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
