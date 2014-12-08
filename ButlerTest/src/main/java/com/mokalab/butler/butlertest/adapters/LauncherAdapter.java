package com.mokalab.butler.butlertest.adapters;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.mokalab.butler.adapter.viewtype.AdapterViewHolder;
import com.mokalab.butler.adapter.viewtype.ViewTypeAdapter;

import java.util.List;

/**
 * Created by Pirdad S on 14-11-21.
 */
public class LauncherAdapter extends ViewTypeAdapter {

    public LauncherAdapter(Context context, List<LaunchModel> content) {
        super(context, content);
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    protected AdapterViewHolder getNewViewHolder(Object rowData) {
        return new LaunchItemViewHolder();
    }

    private static class LaunchItemViewHolder extends AdapterViewHolder<LaunchModel> {

        private TextView mTxt1;

        @Override
        public int getLayoutResource(LaunchModel data) {
            return android.R.layout.simple_list_item_1;
        }

        @Override
        public void onFindViews(Context ctx, View rootView) {
            mTxt1 = findView(rootView, android.R.id.text1);
        }

        @Override
        public void onSetData(Context ctx, LaunchModel data, int position) {
            mTxt1.setText(data.mTitle);
        }
    }

    public static class LaunchModel {

        public String mTitle;
        public Class mLaunchClass;

        public LaunchModel(String title, Class launchClass) {

            mTitle = title;
            mLaunchClass = launchClass;
        }
    }
}
