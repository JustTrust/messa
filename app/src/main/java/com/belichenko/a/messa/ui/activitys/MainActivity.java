package com.belichenko.a.messa.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.belichenko.a.messa.R;
import com.belichenko.a.messa.ui.base.BaseActivity;
import com.belichenko.a.messa.ui.fragments.chat.UserListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private static final String MAIN_START_FLAG = "MainActivity.activity_start_flag";
    public static final int USER_LIST_FLAG = 1;
    public static final int MESSAGE_LIST_FLAG = 2;

    @BindView(R.id.main_container) FrameLayout mMainContainer;

    public static Intent getUserListIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MAIN_START_FLAG, USER_LIST_FLAG);
        return intent;
    }

    public static Intent getMessageListIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MAIN_START_FLAG, MESSAGE_LIST_FLAG);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (getIntent().getIntExtra(MAIN_START_FLAG, USER_LIST_FLAG) == USER_LIST_FLAG) {
            new UserListFragment().show(getSupportFragmentManager(), false);
        }
    }


}
