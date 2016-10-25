package com.nubia.qwei.hal3camera.models.permission;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import java.util.ArrayList;

/**
 * Project: HAL3Camera
 * Package: com.nubia.qwei.hal3camera.models.permission
 * Created by camera on 2016/10/11.
 * 这个类用于对权限进行申请，以及对回调进行处理。记录当前应用的权限状态。
 */

public class PermissionManager {
    /**
     * 这里列举了所有的需要运行时申请的权限，每个危险权限组中选择了一个进行申请。
     */
    public static final String[] PERMISSIONS = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
//            Manifest.permission.READ_CONTACTS,
//            Manifest.permission.ACCESS_COARSE_LOCATION,
//            Manifest.permission.RECORD_AUDIO,
//            Manifest.permission.CALL_PHONE,
//            Manifest.permission.BODY_SENSORS,
//            Manifest.permission.READ_SMS,
//            Manifest.permission.READ_CALENDAR,
    };

    public static  boolean checkPermission(String[] permissions, Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> notGrantedPermissons = new ArrayList<String>();
            for(String permission: permissions){
                boolean granted = activity.checkSelfPermission(permission)== PackageManager.PERMISSION_GRANTED;
                if(!granted){
                    Log.e("wq","request permission: "+ permission);
                    notGrantedPermissons.add(permission);
                }
            }
            if(notGrantedPermissons.isEmpty()){
                return true;
            }else{
                String[] notGrantedPermissionArray = new String[notGrantedPermissons.size()];
                notGrantedPermissons.toArray(notGrantedPermissionArray);
                activity.requestPermissions(notGrantedPermissionArray, 1);
                return false;
            }
        }

        return true;
    }

    public  static void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

    }



}
