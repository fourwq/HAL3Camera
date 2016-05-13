package com.example.qwei.common;

import android.app.Activity;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.os.Bundle;
import android.widget.TextView;

import com.example.qwei.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by camera on 2016/5/12.
 */
public class CameraCharacteristicsActivity extends Activity{
    private TextView mTextView;
    private CameraManager mCameraManager;
    private HashMap<String, CameraCharacteristics>  mMap = new HashMap<>();
    private StringBuilder mBuilder = new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_text);
        mTextView = (TextView) findViewById(R.id.simple_text);
        initCaharacteristicsData();
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
