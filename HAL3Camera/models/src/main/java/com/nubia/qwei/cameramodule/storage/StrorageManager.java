package com.nubia.qwei.cameramodule.storage;

import android.content.Context;

import java.io.File;

/**
 * Project: HAL3Camera
 * Package: com.nubia.qwei.cameramodule.storage
 * Created by camera on 2016/10/20.
 */

public class StrorageManager {

    /**
     * this is the output file for our picture.
     */
    public File mFile;

    public  void prepareFile(Context context){
        mFile = new File(context.getExternalFilesDir(null), "pic.jpg");
    }
}
