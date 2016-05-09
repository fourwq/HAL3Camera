package com.example.qwei.Main.MainUI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.qwei.R;

/**
 * Created by camera on 2016/5/6.
 */
public class MainActivity extends Activity {
    private Button mStartCameraBasic=null;
    private Button mStartCameraManager=null;
    private MyOnClickListener mMyOnClickListener = new MyOnClickListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        mStartCameraBasic = (Button) findViewById(R.id.main_start_camera_basic);
        mStartCameraManager = (Button) findViewById(R.id.main_start_camera_manager);
        mStartCameraBasic.setOnClickListener(mMyOnClickListener);
        mStartCameraManager.setOnClickListener(mMyOnClickListener);
    }

    private class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.main_start_camera_basic:
                    startActivity(com.example.qwei.camera2basic.CameraActivity.class);
                    break;
                case R.id.main_start_camera_manager:
                    startActivity(com.example.qwei.checkCameraManager.CameraManagerActivity.class);
                default:
                    break;
            }
        }
    }

    private void startActivity(Class myClass) {
        Toast.makeText(this, "show "+myClass.toString(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, myClass);
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
