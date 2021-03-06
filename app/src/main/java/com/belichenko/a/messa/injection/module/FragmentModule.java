package com.belichenko.a.messa.injection.module;


import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;


@Module
public class FragmentModule {

    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    Fragment provideFragment() {
        return mFragment;
    }
}
