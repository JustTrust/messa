package com.belichenko.a.messa.injection.component;

import com.belichenko.a.messa.injection.PerActivity;
import com.belichenko.a.messa.injection.module.ActivityModule;
import com.belichenko.a.messa.ui.activitys.MainActivity;
import com.belichenko.a.messa.ui.activitys.StartActivity;

import dagger.Component;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(StartActivity startActivity);

}
