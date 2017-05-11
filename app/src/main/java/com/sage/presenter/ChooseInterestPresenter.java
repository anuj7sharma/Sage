package com.sage.presenter;

import android.content.Context;

import com.sage.R;
import com.sage.adapter.InterestsAdapter;
import com.sage.api.APIHandler;
import com.sage.api.APIResponseInterface;
import com.sage.bean.GetInterestsResponse;
import com.sage.enums.ApiName;
import com.sage.utils.SharedPreferencesHandler;
import com.sage.utils.Utils;
import com.sage.view.activity.DashBoardActivity;
import com.sage.view.fragment.dashboard.ChooseInterestFragment;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Anuj Sharma on 5/11/2017.
 */

public class ChooseInterestPresenter implements APIResponseInterface {
    private Context context;
    private ChooseInterestFragment chooseInterestFragment;
    private ChooseInterestView view;
    private InterestsAdapter mAdapter;

    public ChooseInterestPresenter(Context ctx, ChooseInterestFragment fragment, ChooseInterestView view) {
        this.context = ctx;
        this.chooseInterestFragment = fragment;
        this.view = view;
        init();
    }

    private void init() {
        mAdapter = new InterestsAdapter(context, null);
        view.getRecyclerView().setAdapter(mAdapter);
        //load inerests api and update the view
        ((DashBoardActivity)context).showProgress(true);
        Map<String, String> param = new HashMap<>();
        String userId = SharedPreferencesHandler.getStringValues(context, context.getString(R.string.pref_userid));
        param.put("user_id", userId);
        APIHandler.getInstance().getInterests(param, this, ApiName.GET_INTERESTS_API);
    }

    @Override
    public void onSuccess(Response response, Retrofit retrofit, ApiName api) {
        if (view != null) {
            view.hideProgress();
            switch (api) {
                case GET_INTERESTS_API:
                    if (response.isSuccessful()) {
                        GetInterestsResponse obj = (GetInterestsResponse) response.body();
                        if (obj.getCode() == 1) {
                            //update recycler
                            if (mAdapter != null) mAdapter.updateInterest(obj);
                        } else {
                            Utils.getInstance().showToast(obj.getMessage());
                        }
                    } else {
                        Utils.getInstance().showToast(context.getString(R.string.error_api));
                    }
                    break;
            }
        }
    }

    @Override
    public void onFailure(Throwable t, ApiName api) {
        if (view != null) {
            view.hideProgress();
            if (t.getMessage() != null) Utils.getInstance().showToast(t.getMessage());
            else Utils.getInstance().showToast(context.getString(R.string.error_api));
        }
    }
}
