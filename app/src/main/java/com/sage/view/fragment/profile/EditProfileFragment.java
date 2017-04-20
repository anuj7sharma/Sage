package com.sage.view.fragment.profile;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.sage.R;
import com.sage.utils.Constants;
import com.sage.utils.Utils;
import com.sage.view.activity.BaseFragment;
import com.sage.view.activity.ProfileActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Anuj Sharma on 4/6/2017.
 */

public class EditProfileFragment extends BaseFragment implements View.OnClickListener {
    private View rootView;
    private Toolbar mToolbar;
    private EditText etFirstName,etLastName,etEmail;
    private FloatingActionButton btnEditProfile;
    private CircleImageView mCircleImageView;
    private ProgressBar mProgressBar;
    private Button btnSaveProfile;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initVIews();
    }

    private void initVIews() {
        mToolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
        ((ProfileActivity)getActivity()).setSupportActionBar(mToolbar);
        ((ProfileActivity)getActivity()).getSupportActionBar().setTitle("Edit Profile");
        mToolbar.setNavigationIcon(R.drawable.ic_navigation_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ProfileActivity)getActivity()).oneStepBack();
            }
        });
        mCircleImageView = (CircleImageView)rootView.findViewById(R.id.profile_image);
        mProgressBar = (ProgressBar)rootView.findViewById(R.id.progress_bar);
        etFirstName = (EditText)rootView.findViewById(R.id.et_firstname);
        etLastName = (EditText)rootView.findViewById(R.id.et_lastname);
        etEmail = (EditText)rootView.findViewById(R.id.et_email);
        btnEditProfile = (FloatingActionButton)rootView.findViewById(R.id.btn_edit_profile);
        btnSaveProfile = (Button)rootView.findViewById(R.id.btn_saveProfile);

        mCircleImageView.setOnClickListener(this);
        btnEditProfile.setOnClickListener(this);
        btnSaveProfile.setOnClickListener(this);

        //load information
        Picasso.with(getActivity()).load(Constants.DEF_PROFILE_URL).into(mCircleImageView, new Callback() {
            @Override
            public void onSuccess() {
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.profile_image:
                moveToProfilePicFragment();
                break;
            case R.id.btn_edit_profile:
                moveToProfilePicFragment();
                break;
            case R.id.btn_saveProfile:
                Utils.getInstance().showToast("Save clicked");
                break;
        }
    }

    private void moveToProfilePicFragment() {
        Intent profilePicIntent = new Intent(getActivity(),ProfileActivity.class);
        profilePicIntent.putExtra("destination","profile_pic");
        if (Utils.getInstance().isEqualLollipop()) {
            Pair<View, String> p1 = Pair.create((View) mCircleImageView, "profile_pic");
            ActivityOptions options =
                    ActivityOptions.makeSceneTransitionAnimation(getActivity(), p1);
            getActivity().startActivity(profilePicIntent, options.toBundle());
        } else {
            getActivity().startActivity(profilePicIntent);
        }
    }
}
