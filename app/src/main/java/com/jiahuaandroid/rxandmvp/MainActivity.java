package com.jiahuaandroid.rxandmvp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jakewharton.rxbinding.view.RxView;
import com.jiahuaandroid.basetools.utils.LogUtil;
import com.jiahuaandroid.rxandmvp.databinding.ActivityMainBinding;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        RxView.clicks(binding.btnClick)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        startActivity(intent);
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.e(TAG, "onStart : ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.e(TAG, "onResume : ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.e(TAG, "onPause : ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.e(TAG, "onStop : ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.e(TAG, "onDestroy : ");
    }
}
