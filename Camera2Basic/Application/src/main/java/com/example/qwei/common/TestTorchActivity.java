package com.example.qwei.common;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.qwei.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by camera on 2016/5/11.
 */
public class TestTorchActivity extends Activity {
    private TextView mTextView;
    private Button mButton;
    private CameraManager mCameraManager;
    private MyTorchCallback mCallback = new MyTorchCallback();
    private HashMap<String, Boolean>  mTorchMap = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_text_button);
        mTextView = (TextView) findViewById(R.id.simple_text);
        mButton = (Button) findViewById(R.id.simple_button);
        init();

    }
    private void initButtonListener(){
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Map.Entry entry : mTorchMap.entrySet()){
                    setTorchMode((String) entry.getKey(),! (Boolean) entry.getValue());
                }

            }
        });
    }


    @TargetApi(Build.VERSION_CODES.M)
    private void setTorchMode(String id, boolean enable){
        try {
            mCameraManager.setTorchMode(id, enable);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerCallback();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            unregisterCallback();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void registerCallback(){
        mCameraManager.registerTorchCallback(mCallback, null);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void unregisterCallback(){
        mCameraManager.unregisterTorchCallback(mCallback);
    }


    private void init(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            mButton.setVisibility(View.GONE);
            mTextView.setText("need android M!");
            return;
        }else{
            mButton.setVisibility(View.VISIBLE);
            mButton.setText("torch");
            initButtonListener();
            mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        }

    }

    @TargetApi(Build.VERSION_CODES.M)
    class MyTorchCallback extends CameraManager.TorchCallback{

        @Override
        public void onTorchModeUnavailable(String cameraId) {
            super.onTorchModeUnavailable(cameraId);
            mTextView.append("\ncamera: "+cameraId+" torch unAvailable");
        }

        @Override
        public void onTorchModeChanged(String cameraId, boolean enabled) {
            super.onTorchModeChanged(cameraId, enabled);
            mTextView.append("\ncamera: "+ cameraId+" torch "+ enabled);
            mTorchMap.put(cameraId, enabled);
        }
    }
}
