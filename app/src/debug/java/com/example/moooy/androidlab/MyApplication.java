package com.example.moooy.androidlab;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.stetho.Stetho;

/**
 * Created by stronger on 2016/4/10.
 */
public class MyApplication extends Application {

    private static final String TAG= "MyApplication";

    private static Context application;

    public static Context getInstance(){
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        Log.d(TAG, "onCreate: "+"初始化Stetho");
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
