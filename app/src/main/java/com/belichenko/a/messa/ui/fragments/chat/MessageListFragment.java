package com.belichenko.a.messa.ui.fragments.chat;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.belichenko.a.messa.R;
import com.belichenko.a.messa.ui.adapters.MessageAdapter;
import com.belichenko.a.messa.ui.base.BaseFragment;
import com.belichenko.a.messa.ui.mvp.mvp_viev.MessageListMvpView;
import com.belichenko.a.messa.ui.mvp.presenters.MessageListPresenter;
import com.belichenko.a.messa.util.ViewUtil;
import com.belichenko.a.messaga.MessagePublish;
import com.belichenko.a.messaga.data.models.ChatMessage;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by Belichenko Anton on 14.02.17.
 * mailto: a.belichenko@gmail.com
 */

public class MessageListFragment extends BaseFragment implements MessageListMvpView {

    public static final String USER_NAME_KEY = "MessageListFragment.user_name";
    ArrayList<String> mList = new ArrayList<>();
    private CompositeSubscription compositeSubscription;


    @Inject MessageAdapter mMessageAdapter;
    @Inject MessageListPresenter mPresenter;
    @BindView(R.id.message_list_rv) RecyclerView mUserListRv;
    @BindView(R.id.message_user_image) ImageView mMessageUserImage;
    @BindView(R.id.message_user_name) TextView mMessageUserName;
    @BindView(R.id.new_message_et) EditText mNewMessageEt;
    @BindView(R.id.message_send_iv) ImageView mMessageSendIv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getFragmentComponent().inject(this);
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mPresenter.attachView(this);
        String name = getArguments().getString(USER_NAME_KEY);
        if (name == null) {
            Timber.d("User not define");
            getActivity().onBackPressed();
        }
        mMessageUserName.setText(name);
        mUserListRv.setAdapter(mMessageAdapter);
        mList.add("Hi");
        mList.add("how are you?");

        mMessageAdapter.setUsers(mList);

        mNewMessageEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    sendMessage();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        compositeSubscription = new CompositeSubscription();
        compositeSubscription.add(MessagePublish.getInstance().getMessage()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Timber.d("onNext: s = [%s]", s);
                        addNewMessage(s);
                    }
                })
        );
        observeTyping();
    }

    @Override
    public void onPause() {
        super.onPause();
        compositeSubscription.unsubscribe();
    }

    @Override
    public String getName() {
        return MessageListFragment.class.getSimpleName();
    }

    @Override
    public int getContainer() {
        return R.id.main_container;
    }

    @OnClick({R.id.message_user_image, R.id.message_user_name, R.id.message_send_iv})
    public void onClick(View view) {
        if (!isClickAllowed()) return;
        switch (view.getId()) {
            case R.id.message_user_image:
                break;
            case R.id.message_user_name:
                break;
            case R.id.message_send_iv:
                sendMessage();
                break;
        }
    }

    private void sendMessage() {
        if (mNewMessageEt.getText().length() > 0) {
            mPresenter.sendMessage(new ChatMessage(mNewMessageEt.getText().toString()));
            mNewMessageEt.setText("");
            ViewUtil.hideKeyboard(getActivity());
        }
    }

    public void addNewMessage(String messageText) {
        Timber.d("addNewMessage: messageText = [%s]", messageText);
        mList.add(messageText);
        mMessageAdapter.notifyItemInserted(mList.size());
    }

    private void observeTyping() {
        compositeSubscription.add(RxTextView.textChanges(mNewMessageEt)
                .map(new Func1<CharSequence, String>() {
                    @Override
                    public String call(CharSequence charSequence) {
                        return charSequence.toString();
                    }
                })
                .debounce(3500, TimeUnit.MILLISECONDS)
                .filter(new Func1<CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence charSequence) {
                        return charSequence.length() > 2;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String msg) {
                        mPresenter.sendMessage(new ChatMessage(msg));
                    }
                })
        );
    }
}
