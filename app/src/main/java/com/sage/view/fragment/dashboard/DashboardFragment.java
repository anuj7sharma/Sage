package com.sage.view.fragment.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sage.R;
import com.sage.view.activity.BaseFragment;
import com.sage.view.activity.DashBoardActivity;
import com.sage.view.activity.ProfileActivity;

/**
 * Created by Anuj Sharma on 5/11/2017.
 */

public class DashboardFragment extends BaseFragment implements View.OnClickListener {
    private View rootView;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ImageView btnListType;
    private RelativeLayout categoryType;

    @Override
    public void onStart() {
        super.onStart();
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_dashboard,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_notification:
                break;
            case R.id.action_profile:
                Intent intent = new Intent(getActivity(),ProfileActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dashboard,container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }
    private void initViews() {
        mToolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
        ((DashBoardActivity)getActivity()).setSupportActionBar(mToolbar);
        ((DashBoardActivity)getActivity()).getSupportActionBar().setTitle("");

        categoryType = (RelativeLayout)rootView.findViewById(R.id.category_type);
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_items);
        btnListType = (ImageView)rootView.findViewById(R.id.btn_list_type);

        categoryType.setOnClickListener(this);

//        addDummyData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.category_type:
                break;
        }
    }
}
