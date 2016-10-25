package com.nubia.qwei.cameramodule.utils;

import android.app.Activity;
import android.widget.Toast;

/**
 * Project: HAL3Camera
 * Package: com.nubia.qwei.cameramodule.utils
 * Created by camera on 2016/10/13.
 */

public class Utils {

    /**
     * Shows a {@link Toast} on the UI thread.
     *
     * @param text The message to show
     */
    public static void showToast(final Activity activity,final String text) {

        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


}
