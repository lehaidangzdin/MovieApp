package com.lhd.ontap06.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lhd.ontap06.databinding.ItemCompaniesBinding;
import com.lhd.ontap06.model.movieModel.ProductionCompanies;

import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {
    private List<ProductionCompanies> lsProductionCompanies;

    public CompanyAdapter(List<ProductionCompanies> lsProductionCompanies) {
        this.lsProductionCompanies = lsProductionCompanies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCompaniesBinding binding = ItemCompaniesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ProductionCompanies productionCompanies = lsProductionCompanies.get(position);
        holder.binding.setItem(productionCompanies);
    }

    @Override
    public int getItemCount() {
        return lsProductionCompanies != null ? lsProductionCompanies.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemCompaniesBinding binding;

        public ViewHolder(@NonNull ItemCompaniesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
