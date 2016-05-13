package com.example.qwei.common;

import android.app.Activity;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.qwei.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by camera on 2016/5/12.
 */
public class CameraCharacteristicsActivity extends Activity{
    private TextView mTextView;
    private Button mButton;
    private CameraManager mCameraManager;
    private HashMap<String, CameraCharacteristics>  mMap = new HashMap<>();
    private StringBuilder mBuilder = new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_text_button);
        mTextView = (TextView) findViewById(R.id.simple_text);
        mButton = (Button) findViewById(R.id.simple_button  );
        mButton.setText("save");
        initButton();
        initCaharacteristicsData();
    }

    private void initButton(){
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    File f = new File(Environment.getExternalStorageDirectory(), "CameraAPI2Info.txt");
                    FileOutputStream out=null;
                try {
                    out = new FileOutputStream(f, true);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    out.write(mBuilder.toString().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTextView.setText(getCharacteristicsInfo());
    }

    private void initCaharacteristicsData(){
        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            getCharacteristicsFromManager();
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void getCharacteristicsFromManager() throws CameraAccessException {
        String[] ids = mCameraManager.getCameraIdList();
        for(String id: ids ){
            mMap.put(id, mCameraManager.getCameraCharacteristics(id));
        }
    }

    private String getCharacteristicsInfo(){

        for(Map.Entry entry: mMap.entrySet()){
            String id = (String) entry.getKey();
            CameraCharacteristics characteristics = (CameraCharacteristics) entry.getValue();
            mBuilder.append("\n===============camera: "+ id+"====================\n");
            mBuilder.append(getInfo(characteristics));
        }
        return mBuilder.toString();
    }

    private static   String getInfo(CameraCharacteristics characteristics){
        StringBuilder builder = new StringBuilder();
        List<CameraCharacteristics.Key<?>> mList  = characteristics.getKeys();
        for(CameraCharacteristics.Key<?> item: mList){
            builder.append(item.getName()+ ": "+ Util.getKeyString(characteristics.get(item))+"\n");
        }
        List<CaptureRequest.Key<?>> mRequestList  = characteristics.getAvailableCaptureRequestKeys();
        builder.append("\n\n\n==================request Key===============\n");
        builder.append(getRequestKeyString(mRequestList));
        List<CaptureResult.Key<?>> mResultList  = characteristics.getAvailableCaptureResultKeys();
        builder.append("\n\n\n==================result Key===============\n");
        builder.append(getResultKeyString(mResultList));

        return  builder.toString();
    }

    private static String getResultKeyString(List<CaptureResult.Key<?>> requestList){
        StringBuilder builder = new StringBuilder();
        for(CaptureResult.Key<?> key: requestList){
            builder.append(key.getName()+"\n");
        }
        return builder.toString();
    }

    private static String getRequestKeyString(List<CaptureRequest.Key<?>> requestList){
            StringBuilder builder = new StringBuilder();
        for(CaptureRequest.Key key: requestList){
            builder.append(key.getName()+"\n");
        }
        return builder.toString();
    }

}
