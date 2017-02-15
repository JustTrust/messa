package com.belichenko.a.messa.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.belichenko.a.messa.R;
import com.belichenko.a.messa.ui.activitys.MainActivity;
import com.belichenko.a.messa.ui.base.BaseFragment;
import com.belichenko.a.messa.ui.mvp.mvp_viev.EmailLoginMvpView;
import com.belichenko.a.messa.ui.mvp.presenters.EmailLoginPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Belichenko Anton on 13.02.17.
 * mailto: a.belichenko@gmail.com
 */

public class EmailLoginFragment extends BaseFragment implements EmailLoginMvpView {

    @Inject EmailLoginPresenter mPresenter;
    @BindView(R.id.login_greeting) TextView mLoginGreeting;
    @BindView(R.id.login_email_et) EditText mLoginEmailEt;
    @BindView(R.id.login_pass_et) EditText mLoginPassEt;
    @BindView(R.id.login_login) Button mLoginSinging;

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

    @OnClick(R.id.login_login)
    public void onClick() {
        mPresenter.login(mLoginEmailEt.getText().toString(), mLoginPassEt.getText().toString());
    }

    @Override
    public void login() {
        getActivity().startActivity(MainActivity.getUserListIntent(getContext()));
        getActivity().finish();
    }
}
