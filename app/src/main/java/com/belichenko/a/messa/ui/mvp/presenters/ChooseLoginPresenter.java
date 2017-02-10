package com.belichenko.a.messa.ui.mvp.presenters;

import com.belichenko.a.messa.data.DataManager;
import com.belichenko.a.messa.ui.base.BasePresenter;
import com.belichenko.a.messa.ui.mvp.mvp_viev.ChooseLoginMvpView;

import javax.inject.Inject;

import rx.Subscription;

public class ChooseLoginPresenter extends BasePresenter<ChooseLoginMvpView> {

    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public ChooseLoginPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(ChooseLoginMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

}
