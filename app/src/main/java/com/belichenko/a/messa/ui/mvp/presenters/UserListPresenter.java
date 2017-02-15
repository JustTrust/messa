package com.belichenko.a.messa.ui.mvp.presenters;

import com.belichenko.a.messa.data.DataManager;
import com.belichenko.a.messa.ui.base.BasePresenter;
import com.belichenko.a.messa.ui.mvp.mvp_viev.UserListMvpView;

import javax.inject.Inject;

import rx.Subscription;

public class UserListPresenter extends BasePresenter<UserListMvpView> {

    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public UserListPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(UserListMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

}
