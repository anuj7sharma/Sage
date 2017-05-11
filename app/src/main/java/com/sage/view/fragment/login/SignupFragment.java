package com.sage.view.fragment.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sage.R;
import com.sage.enums.CurrentScreen;
import com.sage.presenter.LoginView;
import com.sage.presenter.SignupPresenter;
import com.sage.utils.Utils;
import com.sage.view.BaseActivity;
import com.sage.view.activity.BaseFragment;
import com.sage.view.activity.LoginActivity;

/**
 * Created by Anuj Sharma on 5/9/2017.
 */

public class SignupFragment extends BaseFragment implements View.OnClickListener, LoginView {
    private View rootView;
    private SignupPresenter presenter;
    private Toolbar mToolbar;
    private EditText etFirstname, etLastname, etEmail, etPassword, etConfirmPwd;
    private TextView tvForgotPwd, tvSignup;
    private Button btnSignup;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_signup, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        presenter = new SignupPresenter(getActivity(), this, this);
    }

    private void initViews() {
        mToolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((LoginActivity) getActivity()).setSupportActionBar(mToolbar);
        ((LoginActivity) getActivity()).getSupportActionBar().setTitle("Register");
        mToolbar.setNavigationIcon(R.drawable.ic_navigation_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.getInstance().hideSoftKeyboard(getActivity(), rootView);
                ((LoginActivity) getActivity()).oneStepBack();
            }
        });
        etFirstname = (EditText) rootView.findViewById(R.id.et_firstname);
        etLastname = (EditText) rootView.findViewById(R.id.et_lastname);
        etEmail = (EditText) rootView.findViewById(R.id.et_email);
        etPassword = (EditText) rootView.findViewById(R.id.et_password);
        etConfirmPwd = (EditText) rootView.findViewById(R.id.et_confirm_password);
        btnSignup = (Button) rootView.findViewById(R.id.btn_signup);

        btnSignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Utils.getInstance().hideSoftKeyboard(getActivity(), rootView);
        switch (v.getId()) {
            case R.id.btn_signup:
                if (Utils.isNetworkAvailable(getActivity())) {
                    if (presenter != null)
                        presenter.checkValidation(etFirstname.getText().toString(), etLastname.getText().toString(),
                                etEmail.getText().toString(), etPassword.getText().toString(), etConfirmPwd.getText().toString());
                } else {
                    Utils.getInstance().showSnakBar(rootView, getString(R.string.error_internet));
                }
                break;
        }
    }

    @Override
    public void showProgress() {
        ((LoginActivity) getActivity()).showProgress(true);
    }

    @Override
    public void hideProgress() {
        ((LoginActivity) getActivity()).showProgress(false);
    }

    @Override
    public void showError(String errorMsg) {
        Utils.getInstance().showSnakBar(rootView, errorMsg);
    }
}
