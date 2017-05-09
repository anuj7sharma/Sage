package com.sage.presenter;

/**
 * Created by Anuj Sharma on 5/9/2017.
 */

public interface LoginView {
    void showProgress();
    void hideProgress();
    void showError(String errorMsg);

}
