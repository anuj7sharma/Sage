package com.sage.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;

import com.sage.R;
import com.sage.enums.CurrentScreen;
import com.sage.view.BaseActivity;
import com.sage.view.fragment.login.LoginFragment;

/**
 * Created by Anuj Sharma on 5/9/2017.
 */

public class LoginActivity extends BaseActivity {
    private RelativeLayout progressLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressLayout = (RelativeLayout)findViewById(R.id.progress_parent);
        progressLayout.setVisibility(View.GONE);
        changeScreen(R.id.login_container, CurrentScreen.LOGIN_SCREEN,false,false,null);
    }

    public void showProgress(boolean isShow){
        if(isShow){
            progressLayout.setVisibility(View.VISIBLE);
        }else{
            progressLayout.setVisibility(View.GONE);
        }
    }
}
