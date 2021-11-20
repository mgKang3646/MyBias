package com.example.actorsearchapplication.adapters;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.actorsearchapplication.ActivityViewListener;
import com.example.actorsearchapplication.R;

public class SelectedViewHolder {

    ImageView selectedImageView, selectedIconImageView;
    TextView selectedName;
    TextView selectedPopularity;
    ActivityViewListener activityViewListener;

    private int id;

    public SelectedViewHolder(View selectedView, ActivityViewListener activityViewListener) {
        selectedImageView = selectedView.findViewById(R.id.iv_selected);
        selectedName = selectedView.findViewById(R.id.tv_name_selected);
        selectedPopularity = selectedView.findViewById(R.id.tv_popularity_selected);
        selectedIconImageView = selectedView.findViewById(R.id.iv_ic_selected);

        this.activityViewListener = activityViewListener;

        //Actor냐 트렌드냐에 따라 구분되어야 함
        selectedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityViewListener.moveActorDetailPage(id);
            }
        });
    }

    public void setActorId(int id){
        this.id = id;
    }
    public ImageView getSelectedImageView() {
        return selectedImageView;
    }
    public ImageView getSelectedIconImageView() {
        return selectedIconImageView;
    }
    public TextView getSelectedName() {
        return selectedName;
    }
    public TextView getSelectedPopularity() {
        return selectedPopularity;
    }

}
