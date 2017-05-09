package com.sage.view.fragment.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sage.R;
import com.sage.enums.CurrentScreen;
import com.sage.presenter.LoginPresenter;
import com.sage.presenter.LoginView;
import com.sage.utils.Utils;
import com.sage.view.BaseActivity;
import com.sage.view.activity.BaseFragment;
import com.sage.view.activity.LoginActivity;

/**
 * Created by Anuj Sharma on 5/9/2017.
 */

public class LoginFragment extends BaseFragment implements View.OnClickListener,LoginView {
    private View rootView;
    private LoginPresenter loginPresenter;
    private EditText etEmail,etPassword;
    private TextView tvForgotPwd, tvSignup;
    private Button btnLogin;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_login,container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        loginPresenter = new LoginPresenter(getActivity(),this,this);
    }

    private void initViews() {
        etEmail = (EditText)rootView.findViewById(R.id.et_email);
        etPassword = (EditText)rootView.findViewById(R.id.et_password);
        btnLogin = (Button)rootView.findViewById(R.id.btn_login);
        tvForgotPwd = (TextView)rootView.findViewById(R.id.tv_forgotPwd);
        tvSignup = (TextView)rootView.findViewById(R.id.tv_signup);

        btnLogin.setOnClickListener(this);
        tvForgotPwd.setOnClickListener(this);
        tvSignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Utils.getInstance().hideSoftKeyboard(getActivity(),rootView);
        switch (v.getId()){
            case R.id.btn_login:
                if(Utils.isNetworkAvailable(getActivity())){
                    if(loginPresenter!=null)
                        loginPresenter.checkValidation(etEmail.getText().toString(),etPassword.getText().toString());
                }else{
                    Utils.getInstance().showSnakBar(rootView,getString(R.string.error_internet));
                }
                break;
            case R.id.tv_forgotPwd:
                break;
            case R.id.tv_signup:
                //Move to Signup Fragment
                ((LoginActivity)getActivity()).changeScreen(R.id.login_container, CurrentScreen.SIGNUP_SCREEN,true,true,null);
                break;

        }
    }

    @Override
    public void showProgress() {
        ((LoginActivity)getActivity()).showProgress(true);
    }

    @Override
    public void hideProgress() {
        ((LoginActivity)getActivity()).showProgress(false);
    }

    @Override
    public void showError(String errorMsg) {
        Utils.getInstance().showSnakBar(rootView,errorMsg);
    }
}
