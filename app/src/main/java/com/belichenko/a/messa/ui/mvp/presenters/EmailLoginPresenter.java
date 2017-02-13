package com.belichenko.a.messa.ui.mvp.presenters;

import com.belichenko.a.messa.data.DataManager;
import com.belichenko.a.messa.ui.base.BasePresenter;
import com.belichenko.a.messa.ui.mvp.mvp_viev.EmailLoginMvpView;
import com.belichenko.a.messaga.MessagaClient;

import javax.inject.Inject;

import rx.Subscription;

public class EmailLoginPresenter extends BasePresenter<EmailLoginMvpView> {

    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public EmailLoginPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(EmailLoginMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void login(String email) {
        MessagaClient.INSTANCE.sendMessage(email);
    }
}
