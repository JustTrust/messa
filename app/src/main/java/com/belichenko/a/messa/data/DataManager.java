package com.belichenko.a.messa.data;

import com.belichenko.a.messa.data.local.DatabaseHelper;
import com.belichenko.a.messa.data.local.PreferencesHelper;
import com.belichenko.a.messa.data.model.Ribot;
import com.belichenko.a.messa.data.remote.HTTPService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;

@Singleton
public class DataManager {

    private final HTTPService mHTTPService;
    private final DatabaseHelper mDatabaseHelper;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public DataManager(HTTPService HTTPService, PreferencesHelper preferencesHelper,
                       DatabaseHelper databaseHelper) {
        mHTTPService = HTTPService;
        mPreferencesHelper = preferencesHelper;
        mDatabaseHelper = databaseHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public Observable<Ribot> syncRibots() {
        return mHTTPService.getRibots()
                .concatMap(new Func1<List<Ribot>, Observable<Ribot>>() {
                    @Override
                    public Observable<Ribot> call(List<Ribot> ribots) {
                        return mDatabaseHelper.setRibots(ribots);
                    }
                });
    }

    public Observable<List<Ribot>> getRibots() {
        return mDatabaseHelper.getRibots().distinct();
    }

}
