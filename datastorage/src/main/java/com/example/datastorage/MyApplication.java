package com.example.datastorage;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by stronger on 2016/4/12.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
