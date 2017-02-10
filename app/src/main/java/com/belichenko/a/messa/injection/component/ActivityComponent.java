package com.belichenko.a.messa.injection.component;

import com.belichenko.a.messa.ui.activitys.MainActivity;

import dagger.Subcomponent;
import com.belichenko.a.messa.injection.PerActivity;
import com.belichenko.a.messa.injection.module.ActivityModule;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
