import android.util.Log;

import com.facebook.stetho.Stetho;

/**
 * Created by stronger on 2016/4/10.
 */
public class MyDebugApplication extends MyApplication {

    private static final String TAG= "com.example.moooy.androidlab.MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
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
