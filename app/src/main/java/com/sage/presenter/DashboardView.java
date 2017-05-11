package com.sage.presenter;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Anuj Sharma on 5/11/2017.
 */

public interface DashboardView {
    void showProgress();
    void hideProgress();
    RecyclerView getRecycler();
    RelativeLayout getCenterIcon();
    ImageView getLogo();
}
