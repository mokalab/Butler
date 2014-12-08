package com.mokalab.butler.butlertest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.mokalab.butler.activities.BaseFragmentActivity;
import com.mokalab.butler.butlertest.adapters.LauncherAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseFragmentActivity implements AdapterView.OnItemClickListener {

    private BaseAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<LauncherAdapter.LaunchModel> content = getLaunchItems();
        mAdapter = new LauncherAdapter(this, content);

        ListView listMain = findView(R.id.listMain);
        listMain.setOnItemClickListener(this);
        listMain.setAdapter(mAdapter);
    }

    private List<LauncherAdapter.LaunchModel> getLaunchItems() {

        List<LauncherAdapter.LaunchModel> content = new ArrayList<LauncherAdapter.LaunchModel>();

        content.add(new LauncherAdapter.LaunchModel("Sharing Test", SharingTestActivity.class));

        return content;
    }

    @Override
    public boolean shouldLog() {
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        LauncherAdapter.LaunchModel item = (LauncherAdapter.LaunchModel) mAdapter.getItem(position);
        startActivity(new Intent(this, item.mLaunchClass));
    }
}
