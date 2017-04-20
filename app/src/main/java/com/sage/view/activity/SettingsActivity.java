package com.sage.view.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.sage.R;
import com.sage.utils.SharedPreferencesHandler;
import com.sage.utils.Utils;


public class SettingsActivity extends DashBoardActivity implements View.OnClickListener {
    private Toolbar mToolbar;
    private RelativeLayout btnEditProfile, btnTerms, btnLogout;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_dashboard, menu);
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        mToolbar.setTitle("Settings");
        mToolbar.setNavigationIcon(R.drawable.ic_navigation_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingsActivity.super.onBackPressed();
            }
        });

        btnEditProfile = (RelativeLayout)findViewById(R.id.btn_editProfile);
        btnTerms = (RelativeLayout)findViewById(R.id.btn_terms);
        btnLogout = (RelativeLayout)findViewById(R.id.btn_logout);

        btnEditProfile.setOnClickListener(this);
        btnTerms.setOnClickListener(this);
        btnLogout.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_editProfile:
                Intent intent = new Intent(this, ProfileActivity.class);
                intent.putExtra("destination","edit_profile");
                startActivity(intent);
                break;
            case R.id.btn_terms:
                break;
            case R.id.btn_logout:
                Utils.getInstance().confirmDialog(this, "Do you want to Logout ?", "logout", new Utils.ConfirmDialogCallbackInterface() {
                    @Override
                    public void onYesClick(String tag) {
                        SharedPreferencesHandler.clearAll(SettingsActivity.this);
                        //Move to Login Screen
//                        Intent logoutintent = new Intent(SettingsActivity.this, LoginActivity.class);
//                        logoutintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        startActivity(logoutintent);
                    }

                    @Override
                    public void onNoClick(String tag) {

                    }
                });

                break;
        }
    }
}
