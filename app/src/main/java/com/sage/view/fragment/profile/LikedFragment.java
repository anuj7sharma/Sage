package com.sage.view.fragment.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sage.R;
import com.sage.adapter.PhotoAdapter;
import com.sage.bean.PhotosBean;
import com.sage.view.activity.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anuj Sharma on 4/6/2017.
 */

public class LikedFragment extends BaseFragment {
    private View rootView;
    private RecyclerView mLikeRecycler;
    private PhotoAdapter mAdapter;
    private List<PhotosBean> likeList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_liked,container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews() {
        StaggeredGridLayoutManager sm = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        mLikeRecycler = (RecyclerView)rootView.findViewById(R.id.recycler_liked);
        mLikeRecycler.setLayoutManager(sm);
        likeList = new ArrayList<>();

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
        mAdapter = new PhotoAdapter(getActivity(),likeList);
        mLikeRecycler.setAdapter(mAdapter);
    }
}
