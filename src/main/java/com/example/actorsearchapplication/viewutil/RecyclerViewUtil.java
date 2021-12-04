package com.example.actorsearchapplication.viewutil;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actorsearchapplication.adapters.DibsRecyclerViewAdapter;

public class RecyclerViewUtil {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;

    public RecyclerViewUtil(RecyclerView recyclerView, RecyclerView.Adapter recyclerViewAdapter){
        this.recyclerView = recyclerView;
        this.recyclerViewAdapter = recyclerViewAdapter;
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public void setLayoutManagerHorizontal(Context context){
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
    }

    public void setLayoutManagerVertical(Context context){
        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
    }

    public void setDibsItemMoveAndSwipeEvent(){
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback
                (ItemTouchHelper.ACTION_STATE_IDLE,ItemTouchHelper.START|ItemTouchHelper.END) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                ((DibsRecyclerViewAdapter)recyclerViewAdapter).deleteItem(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public RecyclerView.Adapter getRecyclerViewAdapter(){
        return recyclerViewAdapter;
    }





}
