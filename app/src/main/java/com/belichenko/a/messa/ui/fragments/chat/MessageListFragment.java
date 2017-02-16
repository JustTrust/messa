package com.belichenko.a.messa.ui.fragments.chat;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.belichenko.a.messa.R;
import com.belichenko.a.messa.ui.adapters.MessageAdapter;
import com.belichenko.a.messa.ui.base.BaseFragment;
import com.belichenko.a.messa.ui.mvp.mvp_viev.MessageListMvpView;
import com.belichenko.a.messa.ui.mvp.presenters.MessageListPresenter;
import com.belichenko.a.messa.util.ViewUtil;

import java.util.ArrayList;
import java.util.MissingFormatArgumentException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Belichenko Anton on 14.02.17.
 * mailto: a.belichenko@gmail.com
 */

public class MessageListFragment extends BaseFragment implements MessageListMvpView {

    public static final String USER_NAME_KEY = "MessageListFragment.user_name";

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
            throw new MissingFormatArgumentException("User not define.");
        }
        mMessageUserName.setText(name);
        mUserListRv.setAdapter(mMessageAdapter);
        ArrayList<String> list = new ArrayList<>();
        list.add("Admin");
        list.add("Albert");
        list.add("Admiral");
        list.add("Suliman");
        list.add("Western");
        list.add("Solid");
        list.add("Upper");
        list.add("Dortmund");
        list.add("Welcome");
        mMessageAdapter.setUsers(list);
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
        if (mNewMessageEt.getText().length() >0)
        mPresenter.sendMessage(mNewMessageEt.getText().toString());
        mNewMessageEt.setText("");
        ViewUtil.hideKeyboard(getActivity());
    }
}
