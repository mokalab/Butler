package com.mokalab.butler.butlertest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mokalab.butler.activities.BaseFragmentActivity;
import com.mokalab.butler.util.SharingUtils;

/**
 * Created by Pirdad S on 14-11-21.
 */
public class SharingTestActivity extends BaseFragmentActivity implements View.OnClickListener {

    @Override
    public boolean shouldLog() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharing);

        Button btnShare = findView(R.id.btnShare);
        btnShare.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        EditText edtUrl = findView(R.id.edtUrl);
        EditText edtBody = findView(R.id.edtBody);

        String url = edtUrl.getText().toString();
        String body = edtBody.getText().toString();
        String subject = "Some Subject!";

        SharingUtils.startSharingUrl(this, subject, url, body);
    }
}
