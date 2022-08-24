package com.lhd.ontap06.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lhd.ontap06.databinding.ItemCastBinding;
import com.lhd.ontap06.model.movieModel.Cast;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder> {
    private List<Cast> lsCasts;

    public CastAdapter(List<Cast> lsCasts) {
        this.lsCasts = lsCasts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCastBinding binding = ItemCastBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Cast cast = lsCasts.get(position);
        holder.binding.setItem(cast);
    }

    @Override
    public int getItemCount() {
        return lsCasts != null ? lsCasts.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemCastBinding binding;

        public ViewHolder(@NonNull ItemCastBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}