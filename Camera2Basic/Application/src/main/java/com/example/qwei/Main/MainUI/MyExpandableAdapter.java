package com.example.qwei.Main.MainUI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.qwei.R;

import java.util.List;

/**
 * Created by camera on 2016/5/11.
 */
class  MyExpandableAdapter extends BaseExpandableListAdapter {
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

        TextView groupHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.main_list_view_group, null);
            groupHolder = (TextView) convertView.findViewById(R.id.main_group_item_description);
            convertView.setTag(groupHolder);
        }else{
            groupHolder = (TextView) convertView.getTag();
        }

        groupHolder.setText(mGroupArray.get(groupPosition));
        return  convertView;
    }





    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        TextView groupHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.main_list_view_child, null);
            groupHolder = (TextView) convertView.findViewById(R.id.main_child_item_description);
            convertView.setTag(groupHolder);
        }else{
            groupHolder = (TextView) convertView.getTag();
        }

        groupHolder.setText(mChildArray.get(groupPosition).get(childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
