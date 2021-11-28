package com.example.actorsearchapplication.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.models.ProductionModel;
import com.example.actorsearchapplication.viewutil.GlideUtil;

public class ProductionRecyclerHolder extends RecyclerView.ViewHolder {

    private ImageView iv_production;
    private TextView tv_production_name;

    public ProductionRecyclerHolder(@NonNull View itemView) {
        super(itemView);
        setFindViewById();
    }

    private void setFindViewById(){
        iv_production = itemView.findViewById(R.id.iv_production);
        tv_production_name = itemView.findViewById(R.id.tv_production_name);
    }

    public void onBind(ProductionModel productionModel){
        GlideUtil.loadProductionImage(itemView.getContext(),productionModel.getLogo_path(),iv_production);
        if(productionModel.getLogo_path() == null) tv_production_name.setText(productionModel.getName());
    }
}
