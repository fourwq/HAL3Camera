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
    private ViewContainer mViewContainer = new ViewContainer();
    private DataAndAction mDataAndAction = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        mDataAndAction = new DataAndAction(this);
        mViewContainer.initListContainer(this, mDataAndAction.getGroupArray(), mDataAndAction.getChildArray());
        mViewContainer.setActionClickedListener(mDataAndAction.getChildClickListener());
    }


}
