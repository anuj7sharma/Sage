package com.sage.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.sage.R;
import com.sage.utils.SharedPreferencesHandler;
import com.sage.utils.Utils;

/**
 * Created by Anuj Sharma on 4/5/2017.
 */

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String userID = SharedPreferencesHandler.getStringValues(this, getString(R.string.pref_userid));
        if (TextUtils.isEmpty(userID)) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, DashBoardActivity.class);
            startActivity(intent);
        }
        finish();
    }
}
