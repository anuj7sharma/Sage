package com.sage.view.fragment.dashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sage.R;
import com.sage.presenter.ChooseInterestPresenter;
import com.sage.presenter.ChooseInterestView;
import com.sage.view.activity.BaseFragment;
import com.sage.view.activity.DashBoardActivity;

/**
 * Created by Anuj Sharma on 5/11/2017.
 */

public class ChooseInterestFragment extends BaseFragment implements ChooseInterestView{
    private View rootView;
    private ChooseInterestPresenter presenter;
    private RecyclerView chooseInterestRecycler;
    private LinearLayoutManager lm;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_choose_interest,container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        presenter = new ChooseInterestPresenter(getActivity(),this,this);
    }

    private void initViews() {
        chooseInterestRecycler = (RecyclerView)rootView.findViewById(R.id.recycler_choose_interest);
        lm = new LinearLayoutManager(getActivity());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        chooseInterestRecycler.setLayoutManager(lm);
    }

    @Override
    public void showProgress() {
        ((DashBoardActivity)getActivity()).showProgress(true);
    }

    @Override
    public void hideProgress() {
        ((DashBoardActivity)getActivity()).showProgress(false);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return chooseInterestRecycler;
    }


}
