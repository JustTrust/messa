package com.belichenko.a.messa.injection.module;

import android.app.Application;
import android.content.Context;

import com.belichenko.a.messa.data.remote.HTTPService;
import com.belichenko.a.messa.injection.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    HTTPService provideRibotsService() {
        return HTTPService.Creator.newRibotsService();
    }

}
