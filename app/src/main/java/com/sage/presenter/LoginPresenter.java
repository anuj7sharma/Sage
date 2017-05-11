package com.sage.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Patterns;

import com.sage.R;
import com.sage.api.APIHandler;
import com.sage.api.APIResponseInterface;
import com.sage.bean.LoginResponse;
import com.sage.enums.ApiName;
import com.sage.utils.SharedPreferencesHandler;
import com.sage.utils.Utils;
import com.sage.view.activity.DashBoardActivity;
import com.sage.view.fragment.login.LoginFragment;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Anuj Sharma on 5/9/2017.
 */

public class LoginPresenter implements APIResponseInterface {
    private Context context;
    private LoginFragment loginFragment;
    private LoginView loginView;

    public LoginPresenter(Context context, LoginFragment loginFragment, LoginView loginView) {
        this.context = context;
        this.loginFragment = loginFragment;
        this.loginView = loginView;
    }

    public void checkValidation(String email, String password) {
        if(loginView!=null){
            if (email.length() == 0 && password.length() == 0) {
                loginView.showError("Please enter all fields");
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                loginView.showError("Please enter valid email");
            } else {
                //Hit LoginAPI
                loginView.showProgress();
                Map<String,String> param = new HashMap<>();
                param.put("email",email);
                param.put("password",password);
                APIHandler.getInstance().checkLogin(param,this,ApiName.LOGIN_API);
            }
        }

    }

    @Override
    public void onSuccess(Response response, Retrofit retrofit, ApiName api) {
        try {
            if (loginView != null) {
                loginView.hideProgress();
            }
            switch (api) {
                case LOGIN_API:
                    if(response.isSuccessful()){
                        LoginResponse obj = (LoginResponse)response.body();
                        if(obj.getCode()==1){
                            if(loginView!=null)loginView.showError(obj.getMessage());

                            SharedPreferencesHandler.setStringValues(context,context.getString(R.string.pref_userid),String.valueOf(obj.getData().get(0).getUid()));

                            Intent intent = new Intent(context, DashBoardActivity.class);
                            intent.putExtra("destination","interest");
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            context.startActivity(intent);
                        }else{
                            if(loginView!=null)loginView.showError(obj.getMessage());
                        }
                    }else{
                        loginView.showError(context.getString(R.string.error_api));
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Throwable t, ApiName api) {
        if (loginView != null) {
            loginView.hideProgress();
            if (t.getMessage() != null) loginView.showError(t.getMessage());
            else loginView.showError(context.getString(R.string.error_api));
        }
    }
}
