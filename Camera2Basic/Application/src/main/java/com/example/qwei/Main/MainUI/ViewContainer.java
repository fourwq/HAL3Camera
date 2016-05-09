package com.example.qwei.Main.MainUI;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.qwei.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by camera on 2016/5/9.
 */
public class ViewContainer {
    private ExpandableListView mExpandableListView = null;
    private List<String> mGroupArray;
    private List<List<String>> mChildArray;
    private MyExpandableAdapter  mMyExpandableAdapter= null;

    public void initListContainer(Activity activity){
        mExpandableListView = (ExpandableListView) activity.findViewById(R.id.main_list_container);
        mMyExpandableAdapter = new MyExpandableAdapter();
        initDataArray();
        mMyExpandableAdapter.init(mGroupArray, mChildArray, activity);
        mExpandableListView.setAdapter(mMyExpandableAdapter);
    }

    private void initDataArray(){
        mGroupArray = new ArrayList<>();
        mChildArray = new ArrayList<>();

        mGroupArray.add("first");
        mGroupArray.add("second");

        List<String> tempArray = new ArrayList<>();
        tempArray.add("1.1");
        tempArray.add("1.2");
        tempArray.add("1.3");
        tempArray.add("1.4");
        tempArray.add("1.5");

        for(int index = 0; index < mGroupArray.size(); index++){
            mChildArray.add(tempArray);
        }

    }
}


class  MyExpandableAdapter extends BaseExpandableListAdapter{
    private List<String> mGroupArray;
    private List<List<String>> mChildArray;
    private Context mContext;

    public void init(List<String> groupArray,List<List<String>> childArray , Context context){
        mGroupArray = groupArray;
        mChildArray = childArray;
        mContext = context;
    }

    @Override
    public int getGroupCount() {
        return mGroupArray.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mChildArray.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupArray.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChildArray.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return  childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return getGenericView(mGroupArray.get(groupPosition));
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String string = mChildArray.get(groupPosition).get(childPosition);
        return getGenericView(string);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private TextView getGenericView(String string){
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 64);
        TextView textView = new TextView(mContext);
        textView.setLayoutParams(layoutParams);
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        textView.setPadding(36, 0, 0, 0);
        textView.setText(string);
        return textView;
    }
}