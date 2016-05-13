package com.example.qwei.common;

import android.app.Activity;
import android.content.Context;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.qwei.R;

/**
 * Created by camera on 2016/5/11.
 */
public class AvailabilityCallbackActivity extends Activity {
    private TextView mTextView;
    private Button mButton;
    private CameraManager mCameraManager;
    private MyAvailabilityCallback mCallback = new MyAvailabilityCallback();
    private boolean mIsRegistered = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_text_button);
        init();
        mTextView = (TextView) findViewById(R.id.simple_text);
        mButton = (Button) findViewById(R.id.simple_button);
        mButton.setVisibility(View.VISIBLE);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(mIsRegistered){
                        unregisterCallback();
                        mButton.setText("register");
                    }else{
                        registerCallback();;
                        mButton.setText("unregister");
                    }
            }
        });
    }

    private void init(){
        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
    }

    private void registerCallback(){
        mCameraManager.registerAvailabilityCallback(mCallback, null);
        mIsRegistered=true;
    }
    private void unregisterCallback(){
        mCameraManager.unregisterAvailabilityCallback(mCallback);
        mIsRegistered= false;
    }
    class MyAvailabilityCallback  extends CameraManager.AvailabilityCallback{
        @Override
        public void onCameraAvailable(String cameraId) {
            super.onCameraAvailable(cameraId);
            String s = mTextView.getText().toString();
            mTextView.setText(s+"\n camera "+cameraId+" available");
        }

        @Override
        public void onCameraUnavailable(String cameraId) {
            super.onCameraUnavailable(cameraId);
            String s = mTextView.getText().toString();
            mTextView.setText(s+"\n camera "+cameraId+" unavailable");

        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        mButton.setText("register");

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterCallback();
    }



}
