package com.belichenko.a.messa.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.belichenko.a.messa.R;
import com.belichenko.a.messa.ui.base.BaseFragment;
import com.belichenko.a.messa.ui.mvp.mvp_viev.ChooseLoginMvpView;
import com.belichenko.a.messa.ui.mvp.presenters.ChooseLoginPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by Belichenko Anton on 10.02.17.
 * mailto: a.belichenko@gmail.com
 */

public class ChooseLoginMethodFragment extends BaseFragment implements ChooseLoginMvpView {

    @Inject ChooseLoginPresenter mPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_method, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getFragmentComponent().inject(this);
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mPresenter.attachView(this);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getContainer() {
        return R.id.start_container;
    }
}
