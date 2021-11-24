package com.example.actorsearchapplication.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.models.ProductionModel;

import java.util.List;

public class ProductionRecyclerAdapter extends RecyclerView.Adapter<ProductionRecyclerHolder>{

    private List<ProductionModel> productions;

    @NonNull
    @Override
    public ProductionRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_production_recycler_holder,parent,false);
        return new ProductionRecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductionRecyclerHolder holder, int position){
        holder.onBind(productions.get(position));
    }

    @Override
    public int getItemCount() {
        if(productions != null){
            return productions.size();
        }
        return 0;
    }

    public void setProductions(List<ProductionModel> productions){
        this.productions = productions;
    }
}
