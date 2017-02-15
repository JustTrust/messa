package com.belichenko.a.messa.ui.fragments.chat;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.belichenko.a.messa.R;
import com.belichenko.a.messa.ui.adapters.UserAdapter;
import com.belichenko.a.messa.ui.base.BaseFragment;
import com.belichenko.a.messa.ui.mvp.mvp_viev.UserListMvpView;
import com.belichenko.a.messa.ui.mvp.presenters.UserListPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Belichenko Anton on 14.02.17.
 * mailto: a.belichenko@gmail.com
 */

public class UserListFragment extends BaseFragment implements UserListMvpView, UserAdapter.OnUserClickListener {

    @Inject UserAdapter mUserAdapter;
    @Inject UserListPresenter mPresenter;
    @BindView(R.id.user_list_rv) RecyclerView mUserListRv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getFragmentComponent().inject(this);
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mPresenter.attachView(this);
        mUserAdapter.setListener(this);
        mUserListRv.setAdapter(mUserAdapter);
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
        mUserAdapter.setUsers(list);
    }

    @Override
    public String getName() {
        return UserListFragment.class.getSimpleName();
    }

    @Override
    public int getContainer() {
        return R.id.main_container;
    }

    @Override
    public void onUserClick(String name) {
        Bundle bundle = new Bundle();
        bundle.putString(MessageListFragment.USER_NAME_KEY, name);
        MessageListFragment messageListFragment = new MessageListFragment();
        messageListFragment.setArguments(bundle);
        messageListFragment.show(getFragmentManager());
    }
}
