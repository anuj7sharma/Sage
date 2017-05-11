package com.sage.presenter;

import android.content.Context;

import com.sage.R;
import com.sage.adapter.TimelineAdapter;
import com.sage.api.APIHandler;
import com.sage.api.APIResponseInterface;
import com.sage.bean.GetInterestsResponse;
import com.sage.bean.TimelineResponse;
import com.sage.enums.ApiName;
import com.sage.utils.Utils;
import com.sage.view.fragment.dashboard.DashboardFragment;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Anuj Sharma on 5/11/2017.
 */

public class DashboardPresenter implements APIResponseInterface {
    private Context context;
    private DashboardFragment fragment;
    private DashboardView view;
    private TimelineAdapter mAdapter;

    public DashboardPresenter(Context ctx, DashboardFragment fragment,DashboardView view){
        this.context = ctx;
        this.fragment = fragment;
        this.view = view;
        init();
    }

    private void init() {
        mAdapter = new TimelineAdapter(context,null);
        view.getRecycler().setAdapter(mAdapter);

        //HIt API and get timeline response.
        Map<String,String> param = new HashMap<>();
        param.put("user_id", Utils.getInstance().getUserID(context));
        APIHandler.getInstance().getTimeLine(param,this,ApiName.DASHBOARD_API);
    }

    @Override
    public void onSuccess(Response response, Retrofit retrofit, ApiName api) {
        if (view != null) {
            view.hideProgress();
            switch (api) {
                case GET_INTERESTS_API:
                    if (response.isSuccessful()) {
                        TimelineResponse obj = (TimelineResponse) response.body();
                        if (obj.getCode() == 1) {
                            //update recycler
//                            if (mAdapter != null) mAdapter.updateList(obj.getData());
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
