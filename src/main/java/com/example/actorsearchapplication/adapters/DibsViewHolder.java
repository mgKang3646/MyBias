package com.example.actorsearchapplication.adapters;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actorsearchapplication.MainActivity;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.utils.MVVMFactory;
import com.example.actorsearchapplication.viewutil.IntentUtil;
import com.example.actorsearchapplication.viewutil.RecyclerViewUtil;

public class DibsViewHolder {

    private View view;
    private ImageView ivNotDibs;
    private RecyclerView recyclerViewDibs;
    private DibsRecyclerViewAdapter dibsRecyclerViewAdapter;
    private RecyclerViewUtil recyclerViewUtil;
    private MainActivity mainActivity;

    public DibsViewHolder(View view, MainActivity mainActivity){
        this.view = view;
        this.mainActivity = mainActivity;
        onBindViewById(view);
        setRecyclerViewUtil();
        refresh();
    }

    public void onBindViewById(View view){
        this.recyclerViewDibs = view.findViewById(R.id.recyclerView_dibs);
        this.ivNotDibs = view.findViewById(R.id.iv_not_dibs);
    }

    public void setRecyclerViewUtil(){
        recyclerViewUtil = new RecyclerViewUtil(recyclerViewDibs,new DibsRecyclerViewAdapter(this));
        recyclerViewUtil.setLayoutManagerVertical(view.getContext());
        recyclerViewUtil.setDibsItemMoveAndSwipeEvent();
    }

    public void refresh(){
        dibsRecyclerViewAdapter = (DibsRecyclerViewAdapter)recyclerViewUtil.getRecyclerViewAdapter();
        dibsRecyclerViewAdapter.setDibs(MVVMFactory.getRoomUtil(view.getContext()).getDibs());
        dibsRecyclerViewAdapter.notifyDataSetChanged();
        if(dibsRecyclerViewAdapter.getItemCount() == 0) ivNotDibs.setVisibility(View.VISIBLE);
        else ivNotDibs.setVisibility(View.INVISIBLE);
    }

    public void moveToDetailActivity(Class klass,int id){
        IntentUtil intentUtil = new IntentUtil(mainActivity);
        intentUtil.moveToDetailActivity(klass,id);
    }
}
