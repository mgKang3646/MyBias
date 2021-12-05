package com.example.actorsearchapplication.adapters;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.example.actorsearchapplication.MainActivity;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.viewmodels.ListViewModel;
import com.example.actorsearchapplication.viewmodels.SelectedViewModel;
import com.example.actorsearchapplication.viewutil.ButtonClickHandler;
import com.example.actorsearchapplication.viewutil.IntentUtil;
import com.example.actorsearchapplication.viewutil.MainViewUtil;
import com.example.actorsearchapplication.viewutil.RecyclerViewUtil;

public class MainContentViewHolder {

    View view;
    Button category_button;
    RecyclerView recyclerView;
    LinearLayout layout_parent_selected;
    //Util
    MainViewUtil mainViewUtil;
    RecyclerViewUtil recyclerViewUtil;
    IntentUtil intentUtil;
    // 뷰모델
    ListViewModel listViewModel;
    SelectedViewModel selectedViewModel;

    MainActivity mainActivity;

    public MainContentViewHolder(View view, MainActivity mainActivity){
        this.mainActivity = mainActivity;
        this.view = view;
        onBindViewById();
        setViewEvent();
        setMainView();
        setRecyclerView();
        setViewModel();
    }

    private void onBindViewById(){
        layout_parent_selected = view.findViewById(R.id.layout_parent_selected);
        recyclerView = view.findViewById(R.id.recyclerView);
        category_button = view.findViewById(R.id.category_button);
        category_button.setVisibility(View.INVISIBLE);
    }

    private void setViewEvent(){
        ButtonClickHandler buttonClickHandler = new ButtonClickHandler(mainActivity);
        buttonClickHandler.setOnClickEvent(category_button);
    }

    private void setRecyclerView(){
        recyclerViewUtil = new RecyclerViewUtil(recyclerView,new MainRecyclerViewAdapter(this));
        recyclerViewUtil.setLayoutManagerHorizontal(view.getContext());
    }

    private void setMainView(){
        mainViewUtil = new MainViewUtil();
        mainViewUtil.inflate(view.getContext(),R.layout.layout_recycler_selected,layout_parent_selected);
        mainViewUtil.createSelectedViewAdapter(this); //
    }

    private void setViewModel(){
        selectedViewModel = new ViewModelProvider(mainActivity).get(SelectedViewModel.class);
        selectedViewModel.observe(mainActivity, mainViewUtil.getMainViewAdapter());
        listViewModel = new ViewModelProvider(mainActivity).get(ListViewModel.class);
        listViewModel.observe(mainActivity,recyclerViewUtil.getRecyclerViewAdapter());
        listViewModel.requestPopularActors();
    }

    public void requestSwitchSelected(int mode, int position){
        selectedViewModel.switchSelected(mode,position);
    }

    public void moveDetailPage(Class klass,int id){
        intentUtil = new IntentUtil(mainActivity);
        intentUtil.moveToDetailActivity(klass,id);
    }

    public void changeContents(int mode){
        if(mode == MainActivity.MODE_MOVIE) listViewModel.requestMovies();
        else if(mode == MainActivity.MODE_TV) listViewModel.requestTvs();
    }

    public void changeMode(int mode){
        if(mode == MainActivity.MODE_ACTOR){
            category_button.setVisibility(View.INVISIBLE);
            listViewModel.requestPopularActors();
        }else if(mode == MainActivity.MODE_MOVIE){
            category_button.setVisibility(View.VISIBLE);
            listViewModel.requestMovies();
        }else if(mode == MainActivity.MODE_TV){
            category_button.setVisibility(View.VISIBLE);
            listViewModel.requestTvs();
        }
    }

    public void setView(View view){
        this.view = view;
    }

}
