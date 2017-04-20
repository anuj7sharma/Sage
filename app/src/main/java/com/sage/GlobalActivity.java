package com.sage;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Anuj Sharma on 2/28/2017.
 */

public class GlobalActivity extends MultiDexApplication {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        if(GlobalActivity.context == null){
            GlobalActivity.context = getApplicationContext();
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static synchronized Context getGlobalContext(){
        return GlobalActivity.context;
    }
}
