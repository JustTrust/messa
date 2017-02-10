package com.belichenko.a.messa.ui.activitys;

import android.os.Bundle;

import com.belichenko.a.messa.R;
import com.belichenko.a.messa.ui.base.BaseActivity;
import com.belichenko.a.messa.ui.fragments.ChooseLoginMethodFragment;

/**
 * Created by Belichenko Anton on 10.02.17.
 * mailto: a.belichenko@gmail.com
 */

public class StartActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        new ChooseLoginMethodFragment().show(getSupportFragmentManager());
    }
}
