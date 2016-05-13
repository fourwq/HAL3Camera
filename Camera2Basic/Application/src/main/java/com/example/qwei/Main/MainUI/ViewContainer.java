package com.example.qwei.Main.MainUI;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.qwei.R;

import java.util.List;

/**
 * Created by camera on 2016/5/9.
 */
public class ViewContainer {
    private ExpandableListView mExpandableListView = null;
    private MyExpandableAdapter  mMyExpandableAdapter= null;
    private MyOnChildClickListener mOnChildClickListener = new MyOnChildClickListener();
    private MyOnGroupClickListener mOnGroupClickListener = new MyOnGroupClickListener();
    private ActionClickedListener mActionClickedListener  = null;


    public void initListContainer(Activity activity, List<String> groupArray, List<List<String>> childArray){
        mExpandableListView = (ExpandableListView) activity.findViewById(R.id.main_list_container);
        mMyExpandableAdapter = new MyExpandableAdapter();
        mMyExpandableAdapter.init(groupArray, childArray, activity);
        mExpandableListView.setAdapter(mMyExpandableAdapter);
        mExpandableListView.setOnChildClickListener(mOnChildClickListener);
        mExpandableListView.setOnGroupClickListener(mOnGroupClickListener);
    }

    public void setActionClickedListener(ActionClickedListener listener) {
        mActionClickedListener = listener;
    }
   interface ActionClickedListener{
         void  onActionClicked(int groupPosition, int childPosition);
    }

    class MyOnGroupClickListener implements ExpandableListView.OnGroupClickListener{

        @Override
        public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
            return false;
        }
    }

    class MyOnChildClickListener implements ExpandableListView.OnChildClickListener{

        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            mActionClickedListener.onActionClicked(groupPosition, childPosition);
            return false;
        }
    }

}


