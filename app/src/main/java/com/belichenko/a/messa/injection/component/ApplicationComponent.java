package com.belichenko.a.messa.injection.component;

import android.app.Application;
import android.content.Context;

import com.belichenko.a.messa.injection.ApplicationContext;

import javax.inject.Singleton;

import dagger.Component;
import com.belichenko.a.messa.data.DataManager;
import com.belichenko.a.messa.data.SyncService;
import com.belichenko.a.messa.data.local.DatabaseHelper;
import com.belichenko.a.messa.data.local.PreferencesHelper;
import com.belichenko.a.messa.data.remote.RibotsService;
import com.belichenko.a.messa.injection.module.ApplicationModule;
import com.belichenko.a.messa.util.RxEventBus;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext
    Context context();
    Application application();
    RibotsService ribotsService();
    PreferencesHelper preferencesHelper();
    DatabaseHelper databaseHelper();
    DataManager dataManager();
    RxEventBus eventBus();

}
