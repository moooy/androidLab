package com.example.moooy.andoridlab.common.utils;

import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * Created by stronger on 2016/4/9.
 */
public class FileUtils {
    private static final String TAG = "FileUtils";

    /**
     * 生成文件夹
     * @param path
     * @return
     */
    public static File mkDir(String path){
        if (path == null) {
            Log.d(TAG, "mkDir: "+"path is null");
            return null;
        }
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }

    public static File createFile(String path,String name){
       File dir = mkDir(path);
        if (name == null) {
            Log.d(TAG, "createFile: "+"fileName is null");
            return null;
        }
        File file = new File(dir,name);
        if (file.exists()) {
            file.delete();
        }else{
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "createFile: "+"file create fail");
            }
        }
        return file;
    }
}
