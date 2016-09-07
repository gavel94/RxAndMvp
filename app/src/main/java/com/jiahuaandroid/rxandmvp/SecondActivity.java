package com.jiahuaandroid.rxandmvp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jiahuaandroid.basetools.utils.LogUtil;
import com.jiahuaandroid.rxandmvp.databinding.ActivityMainBinding;

/**
 * Created by jhhuang on 2016/8/24.
 * QQ:781913268
 * Description：xxx
 */
public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        LogUtil.e(TAG,"onCreate : ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.e(TAG,"onStart : ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.e(TAG,"onResume : ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.e(TAG,"onPause : ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.e(TAG,"onStop : ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.e(TAG,"onDestroy : ");
    }
}
