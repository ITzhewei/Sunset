package com.example.john.sunset;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExpandableActivity extends android.app.ExpandableListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int[] logs = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

        final String[] armTypes = new String[]{"神族", "虫族", "人族"};

        final String[][] arms = new String[][]{
                {"狂战", "龙脊", "点兵"},
                {"小狗", "刺蛇", "飞龙"},
                {"机枪兵", "护士", "幽灵"}
        };
        ExpandableListAdapter adapter = new BaseExpandableListAdapter() {


            @Override
            public int getGroupCount() {
                return armTypes.length;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return arms[groupPosition].length;
            }

            @Override
            public Object getGroup(int groupPosition) {
                return armTypes[groupPosition];
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return arms[groupPosition][childPosition];
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                LinearLayout ll = new LinearLayout(ExpandableActivity.this);
                ll.setOrientation(LinearLayout.HORIZONTAL);
                ImageView imageView = new ImageView(ExpandableActivity.this);
                imageView.setImageResource(logs[groupPosition]);
                ll.addView(imageView);
                TextView textView = getTextView();
                textView.setText(getGroup(groupPosition).toString());
                ll.addView(textView);
                return ll;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                TextView textView = getTextView();
                textView.setText(getChild(groupPosition, childPosition).toString());
                return textView;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }


            private TextView getTextView() {
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 64);
                TextView textView = new TextView(ExpandableActivity.this);
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                textView.setPadding(36, 0, 0, 0);
                textView.setTextSize(20);
                return textView;
            }

        };
        setListAdapter(adapter);
        getExpandableListView().setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent = getIntent();
                intent.putExtra("hero", arms[groupPosition][childPosition]);
                ExpandableActivity.this.setResult(1, intent);
                finish();
                return true;
            }
        });
    }
}
