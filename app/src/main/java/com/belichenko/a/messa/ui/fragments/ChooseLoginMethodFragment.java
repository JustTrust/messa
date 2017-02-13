package com.belichenko.a.messa.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.belichenko.a.messa.R;
import com.belichenko.a.messa.ui.base.BaseFragment;
import com.belichenko.a.messa.ui.mvp.mvp_viev.ChooseLoginMvpView;
import com.belichenko.a.messa.ui.mvp.presenters.ChooseLoginPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Belichenko Anton on 10.02.17.
 * mailto: a.belichenko@gmail.com
 */

public class ChooseLoginMethodFragment extends BaseFragment implements ChooseLoginMvpView {

    @Inject ChooseLoginPresenter mPresenter;
    @BindView(R.id.google_login_bt) Button mGoogleLoginBt;
    @BindView(R.id.fb_login_bt) Button mFbLoginBt;
    @BindView(R.id.email_login_bt) Button mEmailLoginBt;
    @BindView(R.id.login_terms_tv) TextView mLoginTermsTv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_method, container, false);
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

    @OnClick({R.id.google_login_bt, R.id.fb_login_bt, R.id.email_login_bt, R.id.login_terms_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.google_login_bt:
                Toast.makeText(getContext(), getString(R.string.use_email_login_text), Toast.LENGTH_SHORT).show();
                break;
            case R.id.fb_login_bt:
                Toast.makeText(getContext(), getString(R.string.use_email_login_text), Toast.LENGTH_SHORT).show();
                break;
            case R.id.email_login_bt:
                mPresenter.emailLogin();
                break;
            case R.id.login_terms_tv:
                break;
        }
    }

    @Override
    public void goToEmailLogin() {
        new EmailLoginFragment().show(getFragmentManager());
    }
}
