package com.example.qwei.common;

import android.app.Activity;
import android.content.Context;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.qwei.R;

/**
 * Created by camera on 2016/5/11.
 */
public class GetCameraIdListActivity extends Activity {
    private TextView mTextView;
    private CameraManager mCameraManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_text);
        mTextView = (TextView) findViewById(R.id.simple_text);
        doMyWork();
    }

    private void doMyWork(){
        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        mTextView.setText("camera id: \n"+Util.getCameraIdListInfo(mCameraManager));
    }
}
