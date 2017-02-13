package com.belichenko.a.messa;

import android.app.Application;
import android.content.Context;

import com.belichenko.a.messa.injection.component.ApplicationComponent;
import com.belichenko.a.messa.injection.component.DaggerApplicationComponent;
import com.belichenko.a.messa.injection.module.ApplicationModule;
import com.belichenko.a.messaga.MessagaClient;
import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class MessaApplication extends Application implements MessagaClient.ConnectionListener {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
            Timber.plant(new Timber.DebugTree() {
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    return super.createStackElementTag(element)
                            + "::Line:" + element.getLineNumber() + ""
                            + "::" + element.getMethodName() + "()";
                }
            });
            Fabric.with(this, new Crashlytics());
        }
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        MessagaClient.INSTANCE.register(this);

        MessagaClient.INSTANCE.registerConnectionListener(this);
        MessagaClient.INSTANCE.connect();

    }

    public static MessaApplication get(Context context) {
        return (MessaApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError(String error) {

    }
}
