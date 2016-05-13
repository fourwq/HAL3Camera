package com.example.qwei.Main.MainUI;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.qwei.common.AvailabilityCallbackActivity;
import com.example.qwei.common.CameraCharacteristicsActivity;
import com.example.qwei.common.MyData;
import com.example.qwei.common.TestTorchActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by camera on 2016/5/11.
 */
public class DataAndAction {
    private List<String> mGroupArray;
    private List<List<String>> mChildArray;
    private Activity mActivity =null;
    private ChildClickListener mChildClickListener  = new ChildClickListener();

    public DataAndAction(Activity activity){
        mActivity = activity;
        initDataArray();
    }

    public List<String> getGroupArray(){
        return mGroupArray;
    }

    public List<List<String>> getChildArray(){
        return mChildArray;
    }

    public ChildClickListener getChildClickListener(){
        return mChildClickListener;
    }

    class ChildClickListener implements ViewContainer.ActionClickedListener{

        @Override
        public void onActionClicked(int groupPosition, int childPosition) {
            // TODO: 2016/5/11
            String action = mChildArray.get(groupPosition).get(childPosition);
            Toast.makeText(mActivity, action, Toast.LENGTH_SHORT).show();
            if(action == MyData.TAKE_PICTURE_CHILD_TAKE_PICTURE){
                startActivity(com.example.qwei.camera2basic.CameraActivity.class);
            }else if(action == MyData.CHECK_CAMERA_STATUS_CHILD_GET_CAMERAID){
                startActivity(com.example.qwei.common.GetCameraIdListActivity.class);
            }else if(action == MyData.CHECK_CAMERA_STATUS_CHILD_AVAILIBILITY_CALLBACK){
                startActivity(AvailabilityCallbackActivity.class);
            }else if(action == MyData.CHECK_CAMERA_STATUS_CHILD_TORCH){
                startActivity(TestTorchActivity.class);
            }else if(action == MyData.CHECK_CAMERA_STATUS_CHILD_GET_CHARACTERISTICS){
                startActivity(CameraCharacteristicsActivity.class);
            }
        }
    }

    private void startActivity(Class myClass) {
        Intent intent = new Intent(mActivity, myClass);
        mActivity.startActivity(intent);
    }


    private void initDataArray(){
        mGroupArray = new ArrayList<>();
        mChildArray = new ArrayList<>();

        initCheckCameraStatusData();
        initTakePictureData();


    }


    @NonNull
    private void initTakePictureData() {
        mGroupArray.add(1, MyData.TAKE_PICTURE_GROUP);
        List<String> checkCameraStatusArray = new ArrayList<>();
        checkCameraStatusArray.add(0,MyData.TAKE_PICTURE_CHILD_TAKE_PICTURE);
        mChildArray.add(1,checkCameraStatusArray);
    }

    @NonNull
    private void initCheckCameraStatusData() {
        mGroupArray.add(0, MyData.CHECK_CAMERA_STATUS_GROUP);
        List<String> checkCameraStatusArray = new ArrayList<>();
        checkCameraStatusArray.add(0,MyData.CHECK_CAMERA_STATUS_CHILD_GET_CAMERAID);
        checkCameraStatusArray.add(1, MyData.CHECK_CAMERA_STATUS_CHILD_AVAILIBILITY_CALLBACK);
        checkCameraStatusArray.add(2,MyData.CHECK_CAMERA_STATUS_CHILD_TORCH);
        checkCameraStatusArray.add(3,MyData.CHECK_CAMERA_STATUS_CHILD_GET_CHARACTERISTICS);
        mChildArray.add(0,checkCameraStatusArray);
    }
}
