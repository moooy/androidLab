package com.example.moooy.andoridlab.core;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.moooy.andoridlab.R;
import com.example.moooy.andoridlab.constant.AppState;

/**
 * Created by evosoft-C01 on 2016/4/6.
 *
 */
public class AppActivity extends BaseActivity implements IAppActivity {

    private LinearLayout contentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView();
    }

    private void initContentView(){
        contentLayout = new LinearLayout(this);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        ViewGroup decorContent = (ViewGroup) findViewById(android.R.id.content);
        if (decorContent != null) {
            decorContent.removeAllViews();
            decorContent.addView(contentLayout);
        }else{
            showToast(AppState.DECOR_ERROR_STATE);
        }
        LayoutInflater.from(this).inflate(R.layout.common_toolbar,contentLayout,true);
    }

    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID,contentLayout);
    }

}
