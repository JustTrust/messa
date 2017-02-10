package com.belichenko.a.messa.injection.component;

import android.app.Application;
import android.content.Context;

import com.belichenko.a.messa.data.DataManager;
import com.belichenko.a.messa.data.SyncService;
import com.belichenko.a.messa.data.local.DatabaseHelper;
import com.belichenko.a.messa.data.local.PreferencesHelper;
import com.belichenko.a.messa.data.remote.HTTPService;
import com.belichenko.a.messa.injection.ApplicationContext;
import com.belichenko.a.messa.injection.module.ApplicationModule;
import com.belichenko.a.messa.util.RxEventBus;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext
    Context context();
    Application application();
    HTTPService ribotsService();
    PreferencesHelper preferencesHelper();
    DatabaseHelper databaseHelper();
    DataManager dataManager();
    RxEventBus eventBus();

}
