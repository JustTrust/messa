package com.belichenko.a.messa.ui.mvp.presenters;

import com.belichenko.a.messa.data.DataManager;
import com.belichenko.a.messa.ui.base.BasePresenter;
import com.belichenko.a.messa.ui.mvp.mvp_viev.MessageListMvpView;

import javax.inject.Inject;

import rx.Subscription;

public class MessageListPresenter extends BasePresenter<MessageListMvpView> {

    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public MessageListPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(MessageListMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

}
