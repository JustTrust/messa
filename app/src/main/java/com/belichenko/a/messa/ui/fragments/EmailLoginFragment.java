package com.belichenko.a.messa.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.belichenko.a.messa.R;
import com.belichenko.a.messa.ui.base.BaseFragment;
import com.belichenko.a.messa.ui.mvp.mvp_viev.EmailLoginMvpView;
import com.belichenko.a.messa.ui.mvp.presenters.EmailLoginPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by Belichenko Anton on 13.02.17.
 * mailto: a.belichenko@gmail.com
 */

public class EmailLoginFragment extends BaseFragment implements EmailLoginMvpView{

    @Inject EmailLoginPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_email_login, container, false);
        ButterKnife.bind(this, view);
        return view;
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
        return getClass().getSimpleName();
    }

    @Override
    public int getContainer() {
        return R.id.start_container;
    }
}
