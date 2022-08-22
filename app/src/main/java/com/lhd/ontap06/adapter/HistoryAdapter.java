package com.lhd.ontap06.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lhd.ontap06.databinding.ItemHistoryBinding;
import com.lhd.ontap06.model.db.History;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private final List<History> lsHistories;
    private final OnClickItem onClickItem;

    public HistoryAdapter(List<History> lsHistories, OnClickItem onClickItem) {
        this.lsHistories = lsHistories;
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHistoryBinding binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final History history = lsHistories.get(position);
        if (history == null) return;
        holder.itemHistoryBinding.setItem(history);
        holder.itemHistoryBinding.setIClick(onClickItem);
    }

    @Override
    public int getItemCount() {
        return lsHistories != null ? lsHistories.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemHistoryBinding itemHistoryBinding;

        public ViewHolder(@NonNull ItemHistoryBinding itemHistoryBinding) {
            super(itemHistoryBinding.getRoot());
            this.itemHistoryBinding = itemHistoryBinding;
        }
    }

    public interface OnClickItem {
        void onClickItem(History history);

        void onClickDeleteItem(History history);
    }
}
