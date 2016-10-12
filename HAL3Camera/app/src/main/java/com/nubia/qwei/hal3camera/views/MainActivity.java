package com.nubia.qwei.hal3camera.views;

import android.app.Activity;
import android.os.Bundle;

import com.nubia.qwei.hal3camera.R;
import com.nubia.qwei.hal3camera.models.permission.PermissionManager;

public class MainActivity extends Activity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, Camera2BasicFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        PermissionManager.onRequestPermissionsResult(requestCode,permissions, grantResults);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     *
     * @return 从JNI返回字符串
     */
    public native String stringFromJNI();

    public native String stringFromJ();
}
