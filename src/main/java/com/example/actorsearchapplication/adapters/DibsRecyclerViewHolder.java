package com.example.actorsearchapplication.adapters;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.models.RecyclerHolderClickModel;
import com.example.actorsearchapplication.viewutil.GlideUtil;

public class DibsRecyclerViewHolder extends RecyclerView.ViewHolder implements RecyclerViewHolder{
    ImageView profileView;
    TextView popularityView,actorNameView;
    ImageButton deleteButton;
    int id;

    public DibsRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        onBindViewById();

    }

    private void onBindViewById(){
        profileView = itemView.findViewById(R.id.iv_holder_dibs);
        popularityView = itemView.findViewById(R.id.tv_popularity_holder_dibs);
        actorNameView = itemView.findViewById(R.id.tv_actor_name_dibs);
        deleteButton = itemView.findViewById(R.id.btn_delete_dibs);
    }

    public void onBind(ActorModel actorModel){
        id = actorModel.getId();
        popularityView.setText(Math.round(actorModel.getPopularity()*10)/10.0+"");
        actorNameView.setText(actorModel.getName());
        GlideUtil.loadProfileImage(itemView.getContext(),actorModel.getProfile_path(),profileView);
    }

    @Override
    public View getView() {
        return null;
    }

    @Override
    public RecyclerHolderClickModel getRecyclerHolderClickModel() {
        return null;
    }
}
