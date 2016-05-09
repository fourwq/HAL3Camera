package com.example.qwei.checkCameraManager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.qwei.R;
import com.example.qwei.common.Util;

/**
 * Created by camera on 2016/5/6.
 */
public class CameraManagerActivity extends Activity {
    private CameraManager mCameraManager = null;
    private Button mGetCameraIdList = null;
    private Button mRegisterAvailabilityCallback = null;
    private MyOnClickListener mMyOnClickListener = new MyOnClickListener();
    private Button mButtonReset = null;
    private MyAvailabilityCallback mMyAvailabilityCallback = new MyAvailabilityCallback();
    private Button mRegisterTorchCallback = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_manager_activity_layout);
        init();
        mGetCameraIdList = (Button) findViewById(R.id.camera_manager_getcameraidlist);
        mRegisterAvailabilityCallback = (Button) findViewById(R.id.camera_manager_registerAvailabilityCallback);
        mButtonReset = (Button) findViewById(R.id.camera_manager_reset);
        mRegisterTorchCallback = (Button) findViewById(R.id.camera_manager_registerTorchCallback);
        initListeners();
    }

    private void initListeners() {
        mGetCameraIdList.setOnClickListener(mMyOnClickListener);
        mRegisterAvailabilityCallback.setOnClickListener(mMyOnClickListener);
        mButtonReset.setOnClickListener(mMyOnClickListener);
        mRegisterTorchCallback.setOnClickListener(mMyOnClickListener);
    }

    private class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.camera_manager_getcameraidlist:
                    showMessage(getCameraIdListInfo(mCameraManager));
                    break;
                case R.id.camera_manager_registerAvailabilityCallback:
                    registerAvailabilityCallback(mCameraManager);
                    break;
                case R.id.camera_manager_reset:
                    reset(mCameraManager);
                    break;
                case R.id.camera_manager_registerTorchCallback:
                    showMessage("need android M");
                    break;
                default:
                    break;
            }
        }
    }



    private void reset(CameraManager manager){
        Log.e("wq", "reset");
        manager.unregisterAvailabilityCallback(mMyAvailabilityCallback);
        showMessage("reset success");
    }


//    @TargetApi(Build.VERSION_CODES.M)
//    private class MyTorchAvailabilityCallback extends CameraManager.TorchCallback{
//        @Override
//        public void onTorchModeUnavailable(String cameraId) {
//            super.onTorchModeUnavailable(cameraId);
//        }
//
//        @Override
//        public void onTorchModeChanged(String cameraId, boolean enabled) {
//            super.onTorchModeChanged(cameraId, enabled);
//        }
//    }

    private class MyAvailabilityCallback extends CameraManager.AvailabilityCallback{
        @Override
        public void onCameraAvailable(String cameraId) {
            super.onCameraAvailable(cameraId);
            showMessage("onCameraAvailable: "+ cameraId);
        }

        @Override
        public void onCameraUnavailable(String cameraId) {
            super.onCameraUnavailable(cameraId);
            showMessage("onCameraUnavailable: "+ cameraId);
        }
    }

    private void registerAvailabilityCallback(CameraManager manager){
        Log.e("wq", "registerAvailabilityCallback");
        manager.registerAvailabilityCallback(mMyAvailabilityCallback, null);
    }

    private void showMessage(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private static  String  getCameraIdListInfo(CameraManager manager){
        String[] list;
        try {
            list =  manager.getCameraIdList();
        } catch (CameraAccessException e) {
           return "CameraAccessException";
        }
        if(list !=null) {
            return Util.getStringFromStringArray(list);
        }else{
            return "NULL";
        }
    }


    private void init() {
        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        reset(mCameraManager);
    }


}

