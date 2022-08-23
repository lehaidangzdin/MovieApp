package com.lhd.ontap06.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lhd.ontap06.databinding.ItemSearchBinding;
import com.lhd.ontap06.model.movieModel.ResultsSearch;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private final List<ResultsSearch> lsResultsSearches;
    private final IOnClickItem iOnClickItem;

    public SearchAdapter(List<ResultsSearch> lsResultsSearches, IOnClickItem i) {
        this.lsResultsSearches = lsResultsSearches;
        this.iOnClickItem = i;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSearchBinding binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ResultsSearch resultsSearch = lsResultsSearches.get(position);
        if (resultsSearch == null) return;
        holder.binding.setItem(resultsSearch);
        holder.binding.setIClick(this.iOnClickItem);
    }

    @Override
    public int getItemCount() {
        return lsResultsSearches != null ? lsResultsSearches.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemSearchBinding binding;

        public ViewHolder(@NonNull ItemSearchBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface IOnClickItem {
        void onClickItem(ResultsSearch resultsSearch);
    }
}
