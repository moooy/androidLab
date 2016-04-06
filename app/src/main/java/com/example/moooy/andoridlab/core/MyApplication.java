package com.example.moooy.andoridlab.core;

import android.app.Application;
import android.content.Context;

/**
 * Created by evosoft-C01 on 2016/4/6.
 *
 */
public class MyApplication extends Application {

    private static Context application;

    public static Context getInstance(){
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }
}
