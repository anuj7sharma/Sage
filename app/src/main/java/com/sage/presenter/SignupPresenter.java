package com.sage.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Patterns;

import com.sage.R;
import com.sage.api.APIHandler;
import com.sage.api.APIResponseInterface;
import com.sage.bean.LoginResponse;
import com.sage.enums.ApiName;
import com.sage.utils.SharedPreferencesHandler;
import com.sage.view.activity.DashBoardActivity;
import com.sage.view.fragment.login.SignupFragment;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Anuj Sharma on 5/9/2017.
 */

public class SignupPresenter implements APIResponseInterface {
    private Context context;
    private SignupFragment signupFragment;
    private LoginView signupView;

    public SignupPresenter(Context context, SignupFragment loginFragment, LoginView signupView) {
        this.context = context;
        this.signupFragment = loginFragment;
        this.signupView = signupView;
    }

    public void checkValidation(String fname,String lname, String email, String password, String confPwd) {
        if(signupView !=null){
            if (fname.length()==0 || lname.length()==0 || email.length() == 0 || password.length() == 0 || confPwd.length()==0) {
                signupView.showError("Please enter all fields");
            }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                signupView.showError("Please enter valid email");
            }else if (!password.equals(confPwd)) {
                signupView.showError("Passwords do not match");
            }
            else {
                //Hit LoginAPI
                signupView.showProgress();
                Map<String,String> param = new HashMap<>();
                param.put("first_name",fname);
                param.put("last_name",lname);
                param.put("email",email);
                param.put("password",password);
                APIHandler.getInstance().register(param,this,ApiName.SIGNUP_API);
            }
        }

    }

    @Override
    public void onSuccess(Response response, Retrofit retrofit, ApiName api) {
        try {
            if (signupView != null) {
                signupView.hideProgress();
            }
            switch (api) {
                case LOGIN_API:
                    if(response.isSuccessful()){
                        LoginResponse obj = (LoginResponse)response.body();
                        if(obj.getCode()==1){
                            if(signupView!=null)signupView.showError(obj.getMessage());

                            SharedPreferencesHandler.setStringValues(context,context.getString(R.string.pref_userid),String.valueOf(obj.getData().get(0).getUid()));

                            Intent intent = new Intent(context, DashBoardActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            context.startActivity(intent);
                        }else{
                            if(signupView!=null)signupView.showError(obj.getMessage());
                        }
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Throwable t, ApiName api) {
        if (signupView != null) {
            signupView.hideProgress();
            if (t.getMessage() != null) signupView.showError(t.getMessage());
            else signupView.showError(context.getString(R.string.error_api));
        }
    }
}
