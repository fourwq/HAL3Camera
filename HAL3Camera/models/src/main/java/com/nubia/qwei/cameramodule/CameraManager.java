package com.nubia.qwei.cameramodule;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;

/**
 * Project: HAL3Camera
 * Package: com.nubia.qwei.hal3camera.models.camera
 * Created by camera on 2016/10/11.
 */

public class CameraManager {
    private static final String TAG="CameraManager";
    private HandlerThread mHandlerThread;
    public CameraHandler mCameraHandler;





    private void intCameraHandler(){
        Log.i(TAG, "intCameraHandler, current in main thread of id: "+Thread.currentThread().getId());
        mHandlerThread = new HandlerThread("CameraHandlerThread", Thread.NORM_PRIORITY);
        mHandlerThread.start();
        mCameraHandler = new CameraHandler(mHandlerThread.getLooper());
    }

    private class CameraHandler extends Handler{
        public CameraHandler(Looper looper){
            super(looper);
        }
        @Override
        public void handleMessage(Message msg) {
            Log.i(TAG, "handleMessage, current in handler thread of id: "+Thread.currentThread().getId());
            switch (msg.what){
                case MsgType.OPEN_CAMERA:
            }
        }
    }


    public static class MsgType{
        public static final int OPEN_CAMERA = 0;
    }
}
