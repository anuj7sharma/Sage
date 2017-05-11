package com.sage.presenter;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Anuj Sharma on 5/11/2017.
 */

public interface ChooseInterestView {
    void showProgress();
    void hideProgress();
    RecyclerView getRecyclerView();
}
