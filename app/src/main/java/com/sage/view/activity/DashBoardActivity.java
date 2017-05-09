package com.sage.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sage.R;
import com.sage.adapter.PhotoAdapter;
import com.sage.bean.PhotosBean;
import com.sage.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Anuj Sharma on 3/27/2017.
 */

public class DashBoardActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ImageView btnListType;
    private RelativeLayout categoryType;

    private List<PhotosBean> list =new ArrayList<>();
    private StaggeredGridLayoutManager sm;
    private PhotoAdapter mAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_notification:
                break;
            case R.id.action_profile:
                Intent intent = new Intent(this,ProfileActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initViews();
    }

    private void initViews() {
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");

        categoryType = (RelativeLayout)findViewById(R.id.category_type);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_items);
        btnListType = (ImageView)findViewById(R.id.btn_list_type);

        categoryType.setOnClickListener(this);

        addDummyData();
    }

    private void addDummyData() {
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
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.category_type:
                break;
        }
    }
}