package com.sage.view.fragment.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sage.R;
import com.sage.view.activity.BaseFragment;
import com.sage.view.activity.ProfileActivity;
import com.sage.view.activity.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Anuj Sharma on 4/5/2017.
 */

public class ProfileFragment extends BaseFragment {
    private View rootView;
    private Toolbar mToolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private CircleImageView mProfileImg;
    private TextView mName,mBio,mPostsCount, mGroupCount, mForkingCount, mForkedByCount;


    //    MenuItem settingIcon;
    @Override
    public void onStart() {
        super.onStart();
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_profile, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews() {
        mToolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((ProfileActivity) getActivity()).setSupportActionBar(mToolbar);
        ((ProfileActivity) getActivity()).setTitle("");
        mToolbar.setNavigationIcon(R.drawable.ic_navigation_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ProfileActivity)getActivity()).oneStepBack();
            }
        });

        mProfileImg = (CircleImageView)rootView.findViewById(R.id.profile_image);
        mName = (TextView)rootView.findViewById(R.id.name);
        mBio = (TextView)rootView.findViewById(R.id.bio);
        mPostsCount = (TextView)rootView.findViewById(R.id.count_post);
        mGroupCount = (TextView)rootView.findViewById(R.id.count_group);
        mForkingCount = (TextView)rootView.findViewById(R.id.count_forking);
        mForkedByCount = (TextView)rootView.findViewById(R.id.count_forkedBy);

        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        viewPager.setOverScrollMode(View.OVER_SCROLL_IF_CONTENT_SCROLLS);

        setupViewPager(viewPager);

        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new LikedFragment(), "Liked");
        adapter.addFragment(new LikedFragment(), "Downloaded");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}

