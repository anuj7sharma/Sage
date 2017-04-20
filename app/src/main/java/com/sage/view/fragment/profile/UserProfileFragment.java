package com.sage.view.fragment.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sage.adapter.PhotoAdapter;
import com.sage.bean.PhotosBean;
import com.sage.view.activity.BaseFragment;
import com.sage.R;
import com.sage.view.activity.ProfileActivity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Anuj Sharma on 4/19/2017.
 */

public class UserProfileFragment extends BaseFragment implements View.OnClickListener {
    private View rootView;

    private Toolbar mToolbar;
    private RecyclerView mypostRecycler;
    private CircleImageView mProfileImg;
    private TextView mName,mBio,mPostsCount, mGroupCount, mForkingCount, mForkedByCount;
    private TextView btnFork, btnMessage;
    private ImageView btnMoreOption;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_user_profile,container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews() {
        mToolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
        ((ProfileActivity)getActivity()).setSupportActionBar(mToolbar);
        ((ProfileActivity)getActivity()).getSupportActionBar().setTitle("");
        mToolbar.setNavigationIcon(R.drawable.ic_navigation_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ProfileActivity)getActivity()).oneStepBack();
            }
        });

        mypostRecycler = (RecyclerView)rootView.findViewById(R.id.myposts_recycler);
        mProfileImg = (CircleImageView)rootView.findViewById(R.id.profile_image);
        mName = (TextView)rootView.findViewById(R.id.name);
        mBio = (TextView)rootView.findViewById(R.id.bio);
        mPostsCount = (TextView)rootView.findViewById(R.id.count_post);
        mGroupCount = (TextView)rootView.findViewById(R.id.count_group);
        mForkingCount = (TextView)rootView.findViewById(R.id.count_forking);
        mForkedByCount = (TextView)rootView.findViewById(R.id.count_forkedBy);

        btnFork = (TextView)rootView.findViewById(R.id.btn_fork);
        btnMessage = (TextView)rootView.findViewById(R.id.btn_message);
        btnMoreOption = (ImageView) rootView.findViewById(R.id.btn_moreoption);

        btnFork.setOnClickListener(this);
        btnMessage.setOnClickListener(this);
        btnMoreOption.setOnClickListener(this);

        StaggeredGridLayoutManager sm = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mypostRecycler.setLayoutManager(sm);
        loadDummyRelatedData();

    }

    private void loadDummyRelatedData() {
        List<PhotosBean> likeList = new ArrayList<>();
        PhotosBean obj = new PhotosBean();
        obj.photoUrl = "https://i.ytimg.com/vi/x30YOmfeVTE/maxresdefault.jpg";

        likeList.add(obj);

        obj = new PhotosBean();
        obj.photoUrl = "https://upload.wikimedia.org/wikipedia/commons/3/36/Hopetoun_falls.jpg";

        likeList.add(obj);

        obj = new PhotosBean();
        obj.photoUrl = "https://static.pexels.com/photos/33109/fall-autumn-red-season.jpg";

        likeList.add(obj);

        obj = new PhotosBean();
        obj.photoUrl = "https://cdn.pixabay.com/photo/2014/10/15/15/14/man-489744_960_720.jpg";

        likeList.add(obj);

        obj = new PhotosBean();
        obj.photoUrl = "https://static.pexels.com/photos/39811/pexels-photo-39811.jpeg";

        likeList.add(obj);
        PhotoAdapter mAdapter = new PhotoAdapter(getActivity(), likeList);
        mypostRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_moreoption:
                showMoreOptions();
                break;
        }
    }

    private void showMoreOptions() {
        //Creating the instance of PopupMenu
//        PopupMenu popup = new PopupMenu(getActivity(), btnMoreOption);
//        //Inflating the Popup using xml file
//        popup.getMenuInflater().inflate(R.menu.menu_more_option, popup.getMenu());
//
//        //registering popup with OnMenuItemClickListener
//        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            public boolean onMenuItemClick(MenuItem item) {
//                Utils.getInstance().showToast(item.getTitle().toString());
//                return true;
//            }
//        });
//
//        popup.show();//showing popup menu
    }
}
