package com.example.actorsearchapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.utils.MVVMFactory;

import java.util.List;

public class DibsRecyclerViewAdapter extends RecyclerView.Adapter<DibsRecyclerViewHolder>{
    List<ActorModel> dibs;
    DibsViewHolder owner;

    public DibsRecyclerViewAdapter(DibsViewHolder owner){
        this.owner = owner;
    }

    @NonNull
    @Override
    public DibsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recycler_holder_dibs,parent,false);
        return new DibsRecyclerViewHolder(view,owner);
    }

    @Override
    public void onBindViewHolder(@NonNull DibsRecyclerViewHolder holder, int position) {
        holder.onBind(dibs.get(position));
    }

    @Override
    public int getItemCount() {
        if(dibs != null){
            return dibs.size();
        }
        return 0;
    }
    public void setDibs(List<ActorModel> dibs){ this.dibs = dibs; }

    public void deleteItem(int position){
        MVVMFactory.getRoomUtil(null).deleteActor(dibs.get(position));
        dibs.remove(position);
        notifyItemRemoved(position);
    }

}
