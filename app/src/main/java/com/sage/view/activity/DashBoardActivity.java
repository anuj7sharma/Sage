package com.sage.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;

import com.sage.R;
import com.sage.enums.CurrentScreen;
import com.sage.view.BaseActivity;

/**
 * Created by Anuj Sharma on 3/27/2017.
 */

public class DashBoardActivity extends BaseActivity{
    private RelativeLayout progressLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        progressLayout = (RelativeLayout)findViewById(R.id.progress_parent);
        progressLayout.setVisibility(View.GONE);
        if(getIntent()!=null && getIntent().getStringExtra("destination")!=null){
            if(getIntent().getStringExtra("destination").equals("interest")){
                changeScreen(R.id.dashboard_container, CurrentScreen.CHOOSE_INTEREST_SCREEN,false,false,null);
            }
        }else{
            //Move to DashBoard Screen
            changeScreen(R.id.dashboard_container, CurrentScreen.DASHBOARD_SCREEEN,false,false,null);
        }
    }

    public void showProgress(boolean isShow){
        if(isShow){
            progressLayout.setVisibility(View.VISIBLE);
        }else{
            progressLayout.setVisibility(View.GONE);
        }
    }
    /*private void addDummyData() {
        // initialize RecyclerView
        sm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(sm);

        PhotosBean obj;
        obj = new PhotosBean();
        obj.title = "Beautiful Nature view";
        obj.photoName = "Beautiful Nature view";
        obj.availableCount = 3;
        obj.photoUrl = "https://i.ytimg.com/vi/x30YOmfeVTE/maxresdefault.jpg";
        obj.price = "Rs 325";

        list.add(obj);

        obj = new PhotosBean();
        obj.title = "Water fall";
        obj.photoName = "Water fall Image";
        obj.availableCount = 1;
        obj.photoUrl = "https://upload.wikimedia.org/wikipedia/commons/3/36/Hopetoun_falls.jpg";
        obj.price = "Rs 325";

        list.add(obj);

        obj = new PhotosBean();
        obj.title = "Automn Season";
        obj.photoName = "Automn Season View";
        obj.availableCount = 1;
        obj.photoUrl = "https://static.pexels.com/photos/33109/fall-autumn-red-season.jpg";
        obj.price = "Rs 325";

        list.add(obj);

        obj = new PhotosBean();
        obj.title = "Little Strom coming";
        obj.photoName = "Strom coming";
        obj.availableCount = 2;
        obj.photoUrl = "https://cdn.pixabay.com/photo/2014/10/15/15/14/man-489744_960_720.jpg";
        obj.price = "Rs 525";

        list.add(obj);

        obj = new PhotosBean();
        obj.title = "Evening View";
        obj.photoName = "Evening View";
        obj.availableCount = 2;
        obj.photoUrl = "https://static.pexels.com/photos/39811/pexels-photo-39811.jpeg";
        obj.price = "Rs 1425";

        list.add(obj);

        //add adapter
        mAdapter = new PhotoAdapter(this, list);
        mRecyclerView.setAdapter(mAdapter);
    }*/


}