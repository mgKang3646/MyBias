package com.example.actorsearchapplication.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.models.ProductionModel;

public class ProductionRecyclerHolder extends RecyclerView.ViewHolder {

    private ImageView iv_production;
    private TextView tv_production_name;
    private View view;

    public ProductionRecyclerHolder(@NonNull View itemView) {
        super(itemView);
        this.view = itemView;
        iv_production = itemView.findViewById(R.id.iv_production);
        tv_production_name = itemView.findViewById(R.id.tv_production_name);
    }

    public void onBind(ProductionModel productionModel){
        Glide.with(view.getContext()).load("https://image.tmdb.org/t/p/w300/"+productionModel.getLogo_path()).into(iv_production);
        if(productionModel.getLogo_path() == null) tv_production_name.setText(productionModel.getName());
    }
}
